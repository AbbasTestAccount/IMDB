package ir.academy.hamrah.imdb.model.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "http://img.omdbapi.com"


interface ApiService {
}



fun createApiService(): ApiService {

    val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor {
//
//            val oldRequest = it.request()
//
//            val newRequest = oldRequest.newBuilder()
//            newRequest.addHeader("apikey", "44236f51")
//
//            newRequest.method(oldRequest.method, oldRequest.body)
//
//            return@addInterceptor it.proceed(newRequest.build())
//        }
    .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}
