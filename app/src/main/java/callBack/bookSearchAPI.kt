package callBack

import android.os.AsyncTask
import model.Document

class bookSearchAPI constructor(keyword : String) : AsyncTask<String, Void, ArrayList<Document>>() {

    val mTAG : String = "bookSearchAPI"
    val token : String = "KakaoAK a85301089026f3d76b61ac72f59b1d91"
    var keyword : String = ""
    var documentList : ArrayList<Document> = ArrayList()

////    constructor()
//
//    constructor(keyword : String):this(){
//        this.keyword = keyword
//    }
//
//    constructor()

    override fun doInBackground(vararg params: String?): ArrayList<Document> {
        TODO("Not yet implemented")
    }
}