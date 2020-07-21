package callBack

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchRetrofit {
    //KakaoRetrofit을 연결한다
    fun getService() : KakaoRetrofit = retrofit.create(KakaoRetrofit::class.java)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create()) // Gson 사용하기 위해 ConverterFactory에 ㅗ냬ㅜwlwjd
        .build()
}