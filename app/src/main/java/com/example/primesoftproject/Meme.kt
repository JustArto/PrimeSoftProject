package com.example.primesoftproject

import com.google.gson.annotations.SerializedName

data class Meme(
    @SerializedName("name")
    var title: String,
    @SerializedName("url")
    var image: String)