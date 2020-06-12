package fragmentView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlinrecyclerview.R

class BookInfoFragment : Fragment() {
    val TAG : String = "BookInfoFragment"
    lateinit var tvTitle : TextView
    lateinit var tvAuthor : TextView
    lateinit var tvPrice : TextView
    lateinit var  tvISBN : TextView
    lateinit var ivBookImage : ImageView

//    inflater.inflate(R.layout.bookinfo_fragment, container, false);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v(TAG,"onCreateView");

        return inflater.inflate(R.layout.fragment_book_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvAuthor = view.findViewById(R.id.tvAuthor)
        tvPrice = view.findViewById(R.id.tvPrice)
        tvISBN = view.findViewById(R.id.tvISBN)
        ivBookImage = view.findViewById(R.id.ivBookImage)
    }
}