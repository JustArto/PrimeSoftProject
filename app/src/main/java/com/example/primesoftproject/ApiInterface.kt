package com.example.primesoftproject

import com.example.primesoftproject.model.JsonMainCreated
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET("customer/getItems?categoryId=0&parentCategoryId=0&brandId=0&text=&type=1&=")
    fun getBrandData(@Header("language") lang: String) : Call<JsonMainCreated>

    companion object {

        var BASE_URL = "https://api.superautosports.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}