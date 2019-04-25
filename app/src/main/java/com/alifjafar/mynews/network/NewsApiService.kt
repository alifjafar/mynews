package com.alifjafar.mynews.network

import android.app.Application
import android.content.res.Resources
import com.alifjafar.mynews.App
import com.alifjafar.mynews.R
import com.alifjafar.mynews.base.BaseResponse
import com.alifjafar.mynews.models.Article
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String?) : Call<BaseResponse<ArrayList<Article>>>

    companion object {
        private const val baseUrl = "https://newsapi.org/v2/"
        fun create(): NewsApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder().method(original.method(), original.body())
                    requestBuilder.addHeader(
                        "Authorization",
                        "Bearer " + App.applicationContext().getString(R.string.token)
                    )
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NewsApiService::class.java)
        }
    }
}

