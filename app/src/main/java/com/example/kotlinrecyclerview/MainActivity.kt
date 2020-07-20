package com.example.kotlinrecyclerview

import adapter.VerticalAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import callBack.JsonObjectAsyncTask
import com.google.android.material.tabs.TabLayout
import model.Document

class MainActivity : AppCompatActivity() {

    var TAG:String = "MainActivity"
    lateinit var tabLayout:TabLayout
    lateinit var documentList : ArrayList<Document>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        asyncTaskData("JAVA")

        tabLayout = findViewById(R.id.tabLayout)
//        tabLayout.addTab(tabLayout.newTab().setCustomView(createTab))
    }

    fun asyncTaskData(keyword: String){
        Log.v(TAG,"asyncTaskData()_keyword=="+keyword)
        documentList = JsonObjectAsyncTask(keyword).execute().get()
    }

    fun createTabView(tabName: String) : View{
//        View tabView =
    }
}
