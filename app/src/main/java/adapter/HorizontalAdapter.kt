package adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinrecyclerview.R
import fragmentView.BookInfoFragment
import fragmentView.SearchFragment
import model.Document

class HorizontalAdapter(var context: Context
                        ,var documentList: ArrayList<Document>)
    :RecyclerView.Adapter<HorizontalAdapter.CardItem>(){

    var TAG : String = "HorizontalAdapter"
    lateinit var bookInfoFragment : Fragment

//    constructor(context: Context, documentList: ArrayList<Document>, bookInfoFragment: Fragment) : this() {
//        this.context = context
//        this.documentList = documentList
//        this.bookInfoFragment = bookInfoFragment
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItem {
        Log.v(TAG,"onCreateViewHolder()")
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_card,null)
        return CardItem(view)
    }

    override fun onBindViewHolder(holder: CardItem, position: Int) {
        Log.v(TAG,"onBindViewHolder()")

        holder.tv_title.setText(documentList.get(position).title)
        holder.tv_title.isSelected

        var getImageUrl:String = documentList.get(position).thumbnail
        Log.v(TAG,"onBindViewHolder()_Url=="+getImageUrl)
        Glide.with(context).load(getImageUrl).into(holder.iv_poster)
        var drawable : GradientDrawable = context.getDrawable(R.drawable.frame) as GradientDrawable
        holder.iv_poster.background = drawable
        holder.iv_poster.clipToOutline

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                bookInfoFragment = BookInfoFragment(documentList.get(position))


            }
        })
    }

    override fun getItemCount(): Int{
        Log.v(TAG,"getItemCount()== ${documentList.size}")
        return documentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    inner class CardItem(itemView: View) : RecyclerView.ViewHolder(itemView){
        var iv_poster = itemView. findViewById<ImageView>(R.id.iv_poster)
        var tv_title = itemView.findViewById<TextView>(R.id.tv_title)
    }

//    val mClick: View.OnClickListener = object : View.OnClickListener{
//        override fun onClick(v: View?) {
//
//        }
//    }
}