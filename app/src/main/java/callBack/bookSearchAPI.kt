package callBack

import android.os.AsyncTask
import android.util.Log
import model.Document
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class bookSearchAPI constructor(keyword : String) : AsyncTask<String, Void, ArrayList<Document>>() {

    val mTAG : String = "bookSearchAPI"
    val token : String = "KakaoAK a85301089026f3d76b61ac72f59b1d91"
    var keyword : String = keyword
    var documentList : ArrayList<Document> = ArrayList()


    override fun doInBackground(vararg params: String?): ArrayList<Document> {
        TODO("Not yet implemented")
        Log.v(mTAG,"--doInBackground--")
        var url : String = "https://dapi.kakao.com/v3/search/book?target=title"
        url += "&query="+keyword

        try {
            val objUrl : URL = URL(url)
            val con : HttpURLConnection = objUrl.openConnection() as HttpURLConnection
            con.setRequestMethod("GET")
            con.setRequestProperty("Authorization",token)
            val bufferedReader : BufferedReader = BufferedReader(InputStreamReader(con.inputStream))
            val stringBuffer : StringBuffer = StringBuffer()
            var line : String = ""

            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line)
            }
            bufferedReader.close()

            Log.v(mTAG,"stringBuffer=="+stringBuffer)

        }catch (e: Exception){

        }

    }
}