package com.example.primesoftproject.model

import androidx.annotation.Keep

@Keep
data class Data(
    val brands: List<Brand>,
    val items: List<Item>
)