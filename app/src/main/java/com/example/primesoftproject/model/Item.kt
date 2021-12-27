package com.example.primesoftproject.model

import androidx.annotation.Keep

@Keep
data class Item(
    val brandId: Int,
    val categoryId: Int,
    val currentRating: Double,
    val description: String,
    val discount: Int,
    val iconUrl: String,
    val id: Int,
    val listPrice: Double,
    val name: String,
    val price: Double,
    val vendorId: Int
)