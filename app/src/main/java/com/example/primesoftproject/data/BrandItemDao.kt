package com.example.primesoftproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BrandItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(brandItem: BrandItem) //add -> suspend

   @Query("SELECT * FROM brand_item_table ORDER by id ASC")
   fun readAllData(): LiveData<List<BrandItem>>
}