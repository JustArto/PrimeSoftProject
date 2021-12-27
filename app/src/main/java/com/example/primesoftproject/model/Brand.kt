package com.example.primesoftproject.model

import androidx.annotation.Keep

@Keep
data class Brand(
    val categoryIds: List<Int>,
    val iconUrl: String,
    val id: Int,
    val name: String
)