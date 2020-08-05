package fragmentView

import adapter.VerticalAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrecyclerview.R
import model.AdapterVO
import model.Document

class HomeFragment(var contextMain: Context
                   ,var adapterVO: ArrayList<AdapterVO>
                   ,var documents: ArrayList<Document>) : Fragment(){

    var TAG = "HomeFragment"
    lateinit var verticalAdapter: VerticalAdapter

//    constructor(
//        adapterVO: ArrayList<AdapterVO>
//        ,bookInfoFragment: BookInfoFragment
//        ,documents: ArrayList<Document>) : this()
//    {
//        this.adapterVO= adapterVO
//        this.bookInfoFragment= bookInfoFragment
//        this.documents= documents
//    }

    override fun onCreateView(inflater: LayoutInflater
                              ,container: ViewGroup?
                              ,savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home,null);
        Log.v(TAG,"documents== ${documents.get(0).title}")

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewVertical)
        var linearLayoutManager = LinearLayoutManager(contextMain
            ,LinearLayoutManager.VERTICAL
            ,false)
        verticalAdapter = VerticalAdapter(contextMain,adapterVO)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = verticalAdapter
    }
}