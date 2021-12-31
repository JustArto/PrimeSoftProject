package com.example.primesoftproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BrandItem::class], version = 1, exportSchema = false)
abstract class BrandItemDatabase: RoomDatabase() {

    abstract fun brandItemDao(): BrandItemDao

    companion object{
        @Volatile
        private var INSTANCE: BrandItemDatabase? = null
        fun getDatabase(context: Context):BrandItemDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BrandItemDatabase::class.java,
                    "brand_item_database").build()
                INSTANCE= instance
                return instance
            }
        }
    }
}