package com.example.mywalletdigit.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val endpoint =
        "https://www.coinhako.com/api/"
    private val intercepter = Interceptor { chain ->
        //val url = chain.request().url
        val request = chain.request().newBuilder().build()

        chain.proceed(request)
    }

    private val client = OkHttpClient().newBuilder().build()

    fun retrofit():Retrofit = Retrofit.Builder().baseUrl(endpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val CooinSevice = retrofit().create(CooinService::class.java)

}