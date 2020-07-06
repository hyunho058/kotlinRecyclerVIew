package adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import model.Document

class HorizontalAdapter : RecyclerView.Adapter<HorizontalAdapter.CardItem>(){

    var TAG : String = "HorizontalAdapter"
    lateinit var documentList : ArrayList<Document>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItem {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CardItem, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class CardItem(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}