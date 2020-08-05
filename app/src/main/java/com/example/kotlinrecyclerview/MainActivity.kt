package com.example.kotlinrecyclerview

import adapter.ViewType
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    var context:Context = this
    var adapterVO = ArrayList<AdapterVO>()
    var documents = ArrayList<Document>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kakaoAK = "KakaoAK a85301089026f3d76b61ac72f59b1d91"
        kakaoRetrofit = APIClient.getClient().create(KakaoRetrofit::class.java)
        /**
         *  RETROFIT2 를 이요한 REST API 호출
         */
        var callDocumentList : Call<DocumentList> = kakaoRetrofit.getData(kakaoAK,"JAVA")
        callDocumentList.enqueue(object : Callback<DocumentList> {
            override fun onFailure(call: Call<DocumentList>, t: Throwable) {
                Log.v(TAG,"retrofit_onFailure==$t.toString()");
                Log.v(TAG,"retrofit_onFailure==$call.request().toString()");
            }

            override fun onResponse(call: Call<DocumentList>, response: Response<DocumentList>) {
                Log.v(TAG, "retrofit_onResponse==${response.code()}")
                Log.v(TAG, "retrofit_onResponse==${call.request().toString()}")
                Log.v(TAG, "retrofit_response.body().size()==${response.body()!!.documents.size}")
                Log.v(TAG, "retrofit_response.body()_documents.get(0).getAuthors()==${response.body()!!.documents[0].authors}")
                documents = response.body()!!.documents
                addAdapterData("JAVA")
                addAdapterData("C")
                addAdapterData("KOTLIN")
                callFragment()
            }
        })

        /**
         * TabLayout
         */
        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("HOME"
            ,R.drawable.ic_launcher_foreground)))
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("Search"
            ,R.drawable.ic_launcher_foreground)))
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("QR"
            ,R.drawable.ic_launcher_foreground)))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    Log.v(TAG,"onTabReselected()")
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                Log.v(TAG,"onTabUnselected()")

            }
            override fun onTabSelected(p0: TabLayout.Tab?) {
                Log.v(TAG,"onTabSelected()")
                if (p0 != null) {
                    Log.v(TAG,"onTabSelected() POSITION=="+p0.position)
                    when(p0.position){
                        0 -> callFragment()
                        1 -> supportFragmentManager
                            .beginTransaction().replace(R.id.frameLayout, BookInfoFragment()).commit()
                        else -> supportFragmentManager
                            .beginTransaction().replace(R.id.frameLayout, HomeFragment(context,adapterVO,documents)).commit()
                    }
                }
            }
        })
    }

    fun callRetrofit(){

    }


    fun callFragment(){
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        homeFragment = HomeFragment(context,adapterVO, documents)
        fragmentTransaction.replace(R.id.frameLayout, homeFragment).commitAllowingStateLoss()
    }

    fun addAdapterData(keyword: String){
        Log.v(TAG,"addAdapterData()_keyword==${keyword}")
        adapterVO.add(AdapterVO(keyword, ViewType.ItemBookTitle))
        adapterVO.add(AdapterVO(documents, ViewType.ItemHorizontal))
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
