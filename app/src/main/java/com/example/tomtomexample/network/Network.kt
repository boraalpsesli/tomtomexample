package com.example.tomtomexample.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network{
private const val BASE_URL = "https://api.tomtom.com/"

private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val client: OkHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(interceptor)
}.build()


private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

val service: ApiInterface by lazy {
    retrofit.create(ApiInterface::class.java)
}}