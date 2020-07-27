package callBack

import android.os.AsyncTask
import android.util.Log
import model.Document
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class BookSearchAPI constructor(keyword : String) : AsyncTask<String, Void, ArrayList<Document>>() {

    val mTAG : String = "bookSearchAPI"
    val token : String = "KakaoAK a85301089026f3d76b61ac72f59b1d91"
    var keyword : String = keyword
    var documentList : ArrayList<Document> = ArrayList()


    override fun doInBackground(vararg params: String?): ArrayList<Document>? {

        Log.v(mTAG,"--doInBackground--")
        var url : String = "https://dapi.kakao.com/v3/search/book?target=title"
        url += "&query="+keyword

        try {

            /** REQUEST **/
            val objUrl : URL = URL(url)
            val con : HttpURLConnection = objUrl.openConnection() as HttpURLConnection
            con.setRequestMethod("GET")
            con.setRequestProperty("Authorization",token)

            /** RESPONSE **/
            val bufferedReader = BufferedReader(InputStreamReader(con.inputStream))
            val stringBuffer = StringBuffer()
            var line = ""

            // kotlin while 사용 - https://stackoverrun.com/ko/q/11286921
            while(line != null){
                line = bufferedReader.readLine()
                stringBuffer.append(line)
            }
            bufferedReader.close()
            Log.v(mTAG, "stringBuffer==$stringBuffer")
//            var data = stringBuffer.toString()
//            var jsonData = JSONObject(data)
            var documents = JSONObject(stringBuffer.toString()).getJSONArray("documents")
            documentList = ArrayList<Document>()
            for (i in documents.indices){

            }
        }catch (e: Exception){

        }
        return documentList;
    }
}