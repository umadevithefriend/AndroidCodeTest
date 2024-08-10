package com.walmart.nasaapodapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.walmart.nasaapodapi.data.api.ApiService
import com.walmart.nasaapodapi.data.repository.ApodRepository
import com.walmart.nasaapodapi.databinding.ActivityMainBinding
import com.walmart.nasaapodapi.ui.db.ApodDatabase
import com.walmart.nasaapodapi.ui.main.ApodViewModel
import com.walmart.nasaapodapi.ui.main.ApodViewModelFactory
import com.walmart.nasaapodapi.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ApodViewModel by viewModels {
        ApodViewModelFactory(ApodRepository(
            Retrofit.Builder()
                .baseUrl(Constants.NASA_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java),
            ApodDatabase.getDatabase(this).apodDao()
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use DataBindingUtil to bind the layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Bind the ViewModel to the layout
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Observe the ViewModel's LiveData to update UI automatically
        viewModel.apod.observe(this, Observer { apod ->
            apod?.let {
                Picasso.get().load(it.url).into(binding.imageView)
            }
        })

        viewModel.fetchApod(Constants.NASA_API_KEY)
    }
}
