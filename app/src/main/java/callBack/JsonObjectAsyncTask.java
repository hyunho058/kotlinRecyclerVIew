package callBack;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.Document;

public class JsonObjectAsyncTask extends AsyncTask<String, Void, ArrayList<Document>> {

    String TAG = "JsonObjectTest";
    String keyword = "";
    ArrayList<Document> documentList;
    ArrayList docList;

    private String token = "KakaoAK a85301089026f3d76b61ac72f59b1d91";
    private String token1 = "a85301089026f3d76b61ac72f59b1d91";

    public JsonObjectAsyncTask(String keyword) {
        this.keyword = keyword;
    }
    /**
     * background 에서 동작한다는메소드
     * ...은 파라미터가 배열처럼 넘어온다는 뜻 한개도될수있고 여러개될수도있음(가변적)
     * @param params
     * @return
     */
    @Override
    protected ArrayList<Document> doInBackground(String... params) {
        Log.v(TAG,"doInBackground()_Start");
        Log.v(TAG,"doInBackground()_keyword"+keyword);
        String url = "https://dapi.kakao.com/v3/search/book?target=title";
        url += "&query=" + keyword;
        try {
            /*** REQUEST */
            //URL 객체 생성
            URL objUrl = new URL(url);
            Log.v(TAG,"URL=="+url);
            HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();
            // 요청방식 설정(API 문서 참조)
            con.setRequestMethod("GET");
            // 인증 설정
            con.setRequestProperty("Authorization", token);

            /*** RESPONSE */
            //JSON 형테의 OPEN API 데이터를 bufferedReader 를 이용해 가져온다.
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();

            Log.v(TAG, "DEBUG:stringBuffer==" + stringBuffer);
            String data = stringBuffer.toString();
            JSONObject jsonData = new JSONObject(data);
            JSONArray documents = jsonData.getJSONArray("documents");
            docList = new ArrayList();
            documentList = new ArrayList<Document>();
            for(int i = 0; i < documents.length(); i++) {
                JSONObject document = documents.getJSONObject(i);
                Document doc = new Document();
                docList.add(doc);
                documentList.add(doc);
                Log.v(TAG, "DEBUG:document[" + i + "]=" + document);
            }
            Log.v(TAG, "DEBUG:jsonData==" + jsonData);
            Log.v(TAG,"documentList.size()==="+documentList.size());
            Log.v(TAG,"docList==="+documentList.get(0).toString());
            Log.v(TAG,"docList==="+documentList.get(0).getTitle());
        } catch (Exception e) {
            Log.v(TAG, "run()_Exception==" + e.toString());
        }
        return documentList;
    }
}
