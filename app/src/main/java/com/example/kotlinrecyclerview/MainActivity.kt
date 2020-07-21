package com.example.kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import callBack.JsonObjectAsyncTask
import callBack.KakaoRetrofit
import callBack.SearchRetrofit
import com.google.android.material.tabs.TabLayout
import io.reactivex.Single
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

//        val retrofit: KakaoRetrofit = Retrofit.Builder()
////            .baseUrl("https://dapi.kakao.com/v3/search/book?target=title")
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
////            .create(KakaoRetrofit::class.java)
////
////        val call : Call<Document> = retrofit.getData()
////        call.enqueue(object : Callback<List<Document>> {
////            override fun onFailure(call: Call<List<Document>>, t: Throwable) {
////                TODO("Not yet implemented")
////            }
////
////            override fun onResponse(
////                call: Call<List<Document>>,
////                response: Response<List<Document>>
////            ) {
////                TODO("Not yet implemented")
////            }
////        })
        
        SearchRetrofit.getService().getData(keyword = "JAVA")
            .enqueue(object  : Callback<List<Document>>{
                override fun onFailure(call: Call<List<Document>>, t: Throwable) {
                    Log.v(TAG,"SearchRetrofit_onFailure()")
                }

                override fun onResponse(call: Call<List<Document>>, response: Response<List<Document>>) {
                    Log.v(TAG,"SearchRetrofit_onResponse()")
                    Log.v(TAG, "call==$call")
                    Log.v(TAG, "response==$response")
                    Log.v(TAG, "response==${response.body().toString()}")
                    if(response.isSuccessful){
                        Log.v(TAG,"")
                    }
                }
            })

        tabLayout = findViewById(R.id.tabLayout)
//        tabLayout.addTab(tabLayout.newTab().setCustomView(createTab))
    }



    fun asyncTaskData(keyword: String){
        Log.v(TAG,"asyncTaskData()_keyword=="+keyword)
        documentList = JsonObjectAsyncTask(keyword).execute().get()
    }

//    fun createTabView(tabName: String) : View{
//        View tabView =
//    }

}
