package com.example.primesoftproject.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BrandItemViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<BrandItem>>
    private val repository: BrandItemRepository

    init {
        val brandItemDao = BrandItemDatabase.getDatabase(application).brandItemDao()
        repository = BrandItemRepository(brandItemDao)
        readAllData = repository.readAllData
    }
    fun addBrandItem(brandItem: BrandItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBrandItem(brandItem)
        }
    }
}