package com.example.primesoftproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brand_item_table")
data class BrandItem(@PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val url: String
)