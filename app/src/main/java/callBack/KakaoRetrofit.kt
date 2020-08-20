package callBack

import model.Document
import model.DocumentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoRetrofit {

        @GET("v3/search/book?target=title")
        fun getData(
                @Header("Authorization") kakaoAK: String,
                @Query("query") keyword: String
//                ,@Query("sort") sort: String = "recency"
//                ,@Query("page") page: Int = 1
                ,@Query("size") size: Int = 20
) : Call<DocumentList>
}