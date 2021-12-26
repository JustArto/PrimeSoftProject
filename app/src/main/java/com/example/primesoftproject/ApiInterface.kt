package com.example.primesoftproject

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("/get_memes")
    fun getMems() : Call<List<Meme>>

    companion object {

        var BASE_URL = "https://api.imgflip.com"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}