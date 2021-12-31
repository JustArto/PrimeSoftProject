package com.example.primesoftproject.data

import androidx.lifecycle.LiveData

class BrandItemRepository(private val brandItemDao: BrandItemDao) {

    val readAllData: LiveData<List<BrandItem>> = brandItemDao.readAllData()

    fun addBrandItem(brandItem: BrandItem){ //add suspend
        brandItemDao.addItem(brandItem)
    }

}