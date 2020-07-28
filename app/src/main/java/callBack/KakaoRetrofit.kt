package callBack

import io.reactivex.Single
import model.Document
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoRetrofit {
//        @Headers("Authorization: KakaoAK a85301089026f3d76b61ac72f59b1d91")
        @Headers("Authorization: KakaoAK a85301089026f3d76b61ac72f59b1d91")
//        @GET("v3/search/book")
        @GET("v3/search/book?target=title")
        fun getData(
                @Query("query") keyword: String
//                @Query("sort") sort: String = "recency",
//                @Query("page") page: Int = 1,
//                @Query("size") size: Int = 10
        ) : Call<List<Document>>
}