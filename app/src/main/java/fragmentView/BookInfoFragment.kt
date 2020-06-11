package fragmentView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class BookInfoFragment : Fragment() {
    val TAG : String = "BookInfoFragment"
    lateinit var tvTitle : TextView
    lateinit var tvAuthor : TextView
    lateinit var tvPrice : TextView
    lateinit var  tvISBN : TextView
    lateinit var ivBookImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v(TAG,"onCreateView");
        tvTitle =
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}