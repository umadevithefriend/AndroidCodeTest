package com.walmart.nasaapodapi.ui.main

import androidx.lifecycle.*
import com.walmart.nasaapodapi.data.model.ApodResponse
import com.walmart.nasaapodapi.data.repository.ApodRepository
import kotlinx.coroutines.launch

class ApodViewModel(private val repository: ApodRepository) : ViewModel() {

    private val _apod = MutableLiveData<ApodResponse>()
    val apod: LiveData<ApodResponse> get() = _apod

    fun fetchApod(apiKey: String) {
        viewModelScope.launch {
            _apod.value = repository.getApod(apiKey)
        }
    }
}
