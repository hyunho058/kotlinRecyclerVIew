package com.example.kotlinrecyclerview

import adapter.ViewType
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
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
    companion object {
        lateinit var fragmentManager: FragmentManager
    }

    var TAG:String = "MainActivity"
    lateinit var tabLayout:TabLayout
    lateinit var documentList : ArrayList<Document>
    lateinit var kakaoRetrofit : KakaoRetrofit
//    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var serachFragment: SearchFragment
    lateinit var bookInfoFragment: BookInfoFragment
    lateinit var homeFragment: HomeFragment

    var context:Context = this
    var adapterVO = ArrayList<AdapterVO>()
    var documents = ArrayList<Document>()
    val kakaoAK = "KakaoAK xxxxxxx"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kakaoRetrofit = APIClient.getClient().create(KakaoRetrofit::class.java)
        /**
         *  RETROFIT2 를 이요한 REST API 호출
         */
        callRetrofit("JAVA",0)
        callRetrofit("C언어",0)
        callRetrofit("여행",0)
        callRetrofit("KOTLIN",1)
//        var callDocumentList : Call<DocumentList> = kakaoRetrofit.getData(kakaoAK,"JAVA")
//        callDocumentList.enqueue(object : Callback<DocumentList> {
//            override fun onFailure(call: Call<DocumentList>, t: Throwable) {
//                Log.v(TAG,"retrofit_onFailure==$t.toString()");
//                Log.v(TAG,"retrofit_onFailure==$call.request().toString()");
//            }
//
//            override fun onResponse(call: Call<DocumentList>, response: Response<DocumentList>) {
//                Log.v(TAG, "retrofit_onResponse==${response.code()}")
//                Log.v(TAG, "retrofit_onResponse==${call.request().toString()}")
//                Log.v(TAG, "retrofit_response.body().size()==${response.body()!!.documents.size}")
//                Log.v(TAG, "retrofit_response.body()_documents.get(0).getAuthors()==${response.body()!!.documents[0].authors}")
//                documents = response.body()!!.documents
//                addAdapterData("JAVA")
//                addAdapterData("C")
//                addAdapterData("KOTLIN")
//                callFragment()
//            }
//        })

        /**
         * TabLayout
         */
        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("HOME"
            ,R.drawable.house_black_18dp)))
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("Search"
            ,R.drawable.baseline_search_black_18dp)))
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("QR"
            ,R.drawable.baseline_camera_alt_black_18dp)))
        tabLayout.addOnTabSelectedListener(mTab)
    }


    val mTab : TabLayout.OnTabSelectedListener  = object : TabLayout.OnTabSelectedListener{
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
                    1 -> Log.v(TAG,"position== ${p0.position}")
//                        supportFragmentManager
//                        .beginTransaction().replace(R.id.frameLayout, SearchFragment()).commit()
                    else -> supportFragmentManager
                        .beginTransaction().replace(R.id.frameLayout, HomeFragment(context,adapterVO,documents)).commit()
                }
            }
        }
    }

    fun callRetrofit(keyword: String, createFragment: Int){
        var callDocumentList : Call<DocumentList> = kakaoRetrofit.getData(kakaoAK,keyword)
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
                addAdapterData(keyword)
                if (createFragment==1){
                    callFragment()
                }
            }
        })
    }


    fun callFragment(){
        MainActivity.fragmentManager = supportFragmentManager
        fragmentTransaction = MainActivity.fragmentManager.beginTransaction()
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
