package adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fragmentView.BookInfoFragment
import model.Document

class HorizontalAdapter : RecyclerView.Adapter<HorizontalAdapter.CardItem>(){

    var TAG : String = "HorizontalAdapter"
    lateinit var documentList : ArrayList<Document>
    lateinit var context : Context
    lateinit var bookInfoFragment : Fragment

    constructor(context: Context, documentList: ArrayList<Document>, bookInfoFragment: Fragment){
        this.context = context
        this.documentList = documentList
        this.bookInfoFragment = bookInfoFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItem {
        TODO("Not yet implemented")
        Log.v(TAG,"onCreateViewHolder()")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        Log.v(TAG,"getItemCount()")
    }

    override fun onBindViewHolder(holder: CardItem, position: Int) {
        TODO("Not yet implemented")
        Log.v(TAG,"onBindViewHolder()")
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class CardItem(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var iv_poster : ImageView
        lateinit var tv_title : TextView

        constructor(itemView: View, iv_poster ImageView, tv_title TextView) : this() {
            super(itemView)
            this.iv_poster = iv_poster
            this.tv_title = tv_title
        }
    }
}