package com.walmart.nasaapodapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.walmart.nasaapodapi.data.repository.ApodRepository

class ApodViewModelFactory(private val repository: ApodRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApodViewModel::class.java)) {
            return ApodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



