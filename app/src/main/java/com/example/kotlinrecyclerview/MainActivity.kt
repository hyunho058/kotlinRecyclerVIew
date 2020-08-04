package com.example.kotlinrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import callBack.APIClient
import callBack.JsonObjectAsyncTask
import callBack.KakaoRetrofit
import com.google.android.material.tabs.TabLayout
import fragmentView.BookInfoFragment
import fragmentView.HomeFragment
import fragmentView.SearchFragment
import model.AdapterVO
import model.Document
import model.DocumentList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var TAG:String = "MainActivity"
    lateinit var tabLayout:TabLayout
    lateinit var documentList : ArrayList<Document>
    lateinit var kakaoRetrofit : KakaoRetrofit
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var serachFragment: SearchFragment
    lateinit var bookInfoFragment: BookInfoFragment
    lateinit var homeFragment: HomeFragment

    var adapterVO = ArrayList<AdapterVO>()
    var documents = ArrayList<Document>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        asyncTaskData("JAVA")

        /**
         *  RETROFIT2 를 이요한 REST API 호출
         */
        val kakaoAK = "KakaoAK a85301089026f3d76b61ac72f59b1d91"
        val keyword = "JAVA"

        kakaoRetrofit = APIClient.getClient().create(KakaoRetrofit::class.java)
        var callDocumentList : Call<DocumentList> = kakaoRetrofit.getData(kakaoAK,keyword)
        callDocumentList.enqueue(object : Callback<DocumentList> {
            override fun onFailure(call: Call<DocumentList>, t: Throwable) {
                Log.v(TAG,"retrofit_onFailure==$t.toString()");
                Log.v(TAG,"retrofit_onFailure==$call.request().toString()");
            }

            override fun onResponse(call: Call<DocumentList>, response: Response<DocumentList>) {
                Log.v(TAG, "retrofit_onResponse==" + response.code())
                Log.v(TAG, "retrofit_onResponse==" + call.request().toString())
                Log.v(TAG, "retrofit_response.body().size()==" + response.body()!!.documents.size)
                Log.v(TAG, "retrofit_response.body()_documents.get(0).getAuthors()==" + response.body()!!.documents[0].authors)
                documents = response.body()!!.documents
            }
        })

        /**
         * TabLayout
         */
        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("HOME",R.drawable.ic_launcher_foreground)))
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("Search",R.drawable.ic_launcher_foreground)))
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("QR",R.drawable.ic_launcher_foreground)))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
                Log.v(TAG,"onTabReselected()")

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                Log.v(TAG,"onTabUnselected()")

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Log.v(TAG,"onTabSelected()")
                if (p0 != null) {
                    Log.v(TAG,"TapLayout POSITION=="+p0.position)
//                    checkTabLayoutPosition(p0.position)
                }

            }
        })

//        fragmentManager = supportFragmentManager
//
//        fragmentTransaction = fragmentManager.beginTransaction()
//        var bundle = Bundle()
//        homeFragment = HomeFragment(adapterVO, bookInfoFragment, documents)
////            bundle.putSerializable("list", documents)
////            homeFragment.
//        fragmentTransaction.replace(R.id.frameLayout, homeFragment).commitAllowingStateLoss()

    }

    fun checkTabLayoutPosition(tabPosition: Int): Int {
        return when(tabPosition){
            0 -> supportFragmentManager
                .beginTransaction().replace(R.id.frameLayout, HomeFragment(adapterVO, bookInfoFragment,documents)).commit()
            1 -> supportFragmentManager
                .beginTransaction().replace(R.id.frameLayout, BookInfoFragment()).commit()
            else -> supportFragmentManager
                .beginTransaction().replace(R.id.frameLayout, HomeFragment(adapterVO, bookInfoFragment,documents)).commit()
        }
    }


    fun asyncTaskData(keyword: String){
        Log.v(TAG,"asyncTaskData()_keyword=="+keyword)
        documentList = JsonObjectAsyncTask(keyword).execute().get()
        Log.v(TAG, "asyncTaskData()_documentList_title==${documentList.get(0).title.toString()}")
    }

    fun createTabView(tabName: String, iconImage: Int) : View{
        val tabView : View = layoutInflater.inflate(R.layout.custom_tab,null)
        val tvTab : TextView = tabView.findViewById(R.id.tvTab)
        tvTab.setText(tabName)
        val ivtab : ImageView = tabView.findViewById(R.id.ivTab)
        ivtab.setImageResource(iconImage)
        return tabView
    }
}
