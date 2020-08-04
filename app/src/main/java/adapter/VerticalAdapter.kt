package adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrecyclerview.R
import fragmentView.BookInfoFragment
import model.AdapterVO

class VerticalAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var TAG = "VerticalAdapter"
    lateinit var view : View
    lateinit var context: Context
    lateinit var adapterList : ArrayList<AdapterVO>
    lateinit var horizontalAdapter: HorizontalAdapter
    lateinit var bookInfoFragment: BookInfoFragment

    constructor(context: Context, adapterList: ArrayList<AdapterVO>, bookInfoFragment: BookInfoFragment) : this() {
        this.context=context
        this.adapterList=adapterList
        this.bookInfoFragment = bookInfoFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.v(TAG,"onCreateViewHolder()_viewType== $viewType")
        var inflater: LayoutInflater
                =context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(viewType == ViewType.ItemBookTitle){
            view=inflater.inflate(R.layout.title_item, parent,false)
            return BookTitleItem(view)
        }else{
            view=inflater.inflate(R.layout.recycler_horizontal, parent, false)
            return HorizontalItem(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.v(TAG,"onBindViewHolder()==${holder} position==$position")
        if (holder is BookTitleItem){
            holder.tvBookTitle.setText(adapterList.get(position).bookTitle)
        }else if(holder is HorizontalItem){
            (holder as HorizontalItem).recyclerHorizontal.layoutManager
//            (LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false))
            horizontalAdapter =
                HorizontalAdapter(context, adapterList.get(position).documentList,bookInfoFragment)
            holder.recyclerHorizontal.adapter
        }
    }

    override fun getItemCount(): Int {
        Log.v(TAG,"getItemCount()== ${adapterList.size}")
        return adapterList.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.v(TAG,"getItemViewType()==${adapterList.get(position).viewType} position==$position")
        return adapterList.get(position).viewType
    }

    inner class BookTitleItem(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvBookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
//        constructor(itemView: View) : this(){
//            tvBookTitle = itemView.findViewById(R.id.tvBookTitle)
//        }
    }

    inner class HorizontalItem(itemView: View): RecyclerView.ViewHolder(itemView){
        var recyclerHorizontal : RecyclerView = itemView.findViewById(R.id.recyclerViewHorizontal) as RecyclerView

//        constructor(itemView: View) : this() {
//            this.recyclerHorizontal=itemView.findViewById(R.id.recyclerViewHorizontal)
//        }
    }
}