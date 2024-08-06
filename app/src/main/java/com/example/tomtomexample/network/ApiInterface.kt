package com.example.tomtomexample.network

import com.example.tomtomexample.BuildConfig
import com.example.tomtomexample.response.testResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/2/search/{term}.json")
     fun doSearchByQuery(@Query("term") term:String,
                                @Query("key") apiKey:String=BuildConfig.TOMTOM_API_KEY): Call<testResponse>
}