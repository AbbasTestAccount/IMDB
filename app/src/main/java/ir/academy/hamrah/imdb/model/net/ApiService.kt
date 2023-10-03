package ir.academy.hamrah.imdb.model.net

import ir.academy.hamrah.imdb.model.data.MovieInfo
import ir.academy.hamrah.imdb.model.data.MoviesList
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "http://www.omdbapi.com"


interface ApiService {

    @GET("/")
    suspend fun getMoviesList(@Query("s") searchTerm: String, @Query("page") page: Int = 1): MoviesList

    @GET("/")
    suspend fun getMovieInfo(@Query("i") imdbId: String) : MovieInfo


}



fun createApiService(): ApiService {


    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {

            val original = it.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", "44236f51") //this key should be hidden
                                                                    //and i shouldn't push it in my github
                                                                    //but because it's a sample project i didn't hide that
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
