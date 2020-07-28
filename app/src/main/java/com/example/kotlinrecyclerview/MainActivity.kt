package com.example.kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.PointerIcon
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import callBack.JsonObjectAsyncTask
import callBack.KakaoRetrofit
import callBack.SearchRetrofit
import com.google.android.material.tabs.TabLayout
import fragmentView.BookInfoFragment
import fragmentView.HomeFragment
import io.reactivex.Single
import kotlinx.android.synthetic.main.custom_tab.*
import model.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var TAG:String = "MainActivity"
    lateinit var tabLayout:TabLayout
    lateinit var documentList : ArrayList<Document>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        asyncTaskData("JAVA")
        
//        SearchRetrofit.getService().getData(keyword = "JAVA")
//            .enqueue(object : Callback<List<Document>>{
//                override fun onFailure(call: Call<List<Document>>, t: Throwable) {
//                    Log.v(TAG,"SearchRetrofit_onFailure()")
//                }
//                override fun onResponse(
//                    call: Call<List<Document>>, response: Response<List<Document>>) {
//                    Log.v(TAG,"SearchRetrofit_onResponse()")
//                    Log.v(TAG, "call==$call")
//                    Log.v(TAG, "response==$response")
//                    Log.v(TAG, "response==${response.body().toString()}")
//                    if(response.isSuccessful){
//                        Log.v(TAG,"")
//                    }
//                }
//            })

        val thread = Thread(Runnable(){
            Log.v(TAG,"THREAD START")
            val retrofit: KakaoRetrofit = Retrofit.Builder()
                .baseUrl("https://dapi.kakao.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoRetrofit::class.java)

            val call: Call<List<Document>> = retrofit.getData("java")
            call.enqueue(object : Callback<List<Document>>{
                override fun onFailure(call: Call<List<Document>>, t: Throwable) {
                    Log.v(TAG,"SearchRetrofit_onFailure()")
                }
                override fun onResponse(call: Call<List<Document>>, response: Response<List<Document>>) {
                    Log.v(TAG,"SearchRetrofit_onResponse()")
                    Log.v(TAG, "call==$call")
                    Log.v(TAG, "response==$response")
                    Log.v(TAG, "response==${response.body().toString()}")
                }
            })
        })

        thread.start()


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
                    checkTabLayoutPosition(p0.position)
                }

            }
        })
    }
    fun checkTabLayoutPosition(tabPosition: Int): Int {
        return when(tabPosition){
            0 -> supportFragmentManager
                .beginTransaction().replace(R.id.frameLayout, HomeFragment()).commit()
            1 -> supportFragmentManager
                .beginTransaction().replace(R.id.frameLayout, BookInfoFragment()).commit()
            else -> supportFragmentManager
                .beginTransaction().replace(R.id.frameLayout, HomeFragment()).commit()
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
