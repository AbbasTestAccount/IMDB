package ir.academy.hamrah.imdb.model.net

import ir.academy.hamrah.imdb.model.data.MovieInfo
import ir.academy.hamrah.imdb.model.data.MoviesList
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


const val BASE_URL = "http://img.omdbapi.com"


interface ApiService {

    //todo: add path instead of Batman
    @GET("/?s=[Batman]")
    suspend fun getMoviesList(): MoviesList

    //http://www.omdbapi.com/?i=tt0372784
    @GET("/?i={imdbId}")
    suspend fun getMovieInfo(@Path("imdbId") imdbId: String) : MovieInfo


}



fun createApiService(): ApiService {


    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {

            val original = it.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", "44236f51")
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            return@addInterceptor it.proceed(request)

        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}
