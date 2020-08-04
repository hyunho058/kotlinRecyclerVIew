package fragmentView

import adapter.VerticalAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinrecyclerview.R
import model.AdapterVO
import model.Document

class HomeFragment() : Fragment(){

    var TAG = "HomeFragment"
    lateinit var adapterVO: ArrayList<AdapterVO>
    lateinit var bookInfoFragment: BookInfoFragment
    lateinit var verticalAdapter: VerticalAdapter
    lateinit var documents: ArrayList<Document>


    constructor(
        adapterVO: ArrayList<AdapterVO>
        ,bookInfoFragment: BookInfoFragment
        ,documents: ArrayList<Document>) : this()
    {
        this.adapterVO= adapterVO
        this.bookInfoFragment= bookInfoFragment
        this.documents= documents
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home,null);
        var context = container?.context

        Log.v(TAG,"bundle==${arguments?.get("list")}")
        documents= arguments?.get("list") as ArrayList<Document>
        Log.v(TAG,"documents== ${documents.get(0).title}")
        return view;
    }
}