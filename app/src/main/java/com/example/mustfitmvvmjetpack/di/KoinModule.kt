package com.example.mustfitmvvmjetpack.di

import com.example.mustfitmvvmjetpack.viewmodel.AuthenticationViewModel
import com.example.mustfitmvvmjetpack.viewmodel.DataViewModel
import com.example.mustfitmvvmjetpack.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {
    val viewModelModule = module {
        viewModel { AuthenticationViewModel(get()) }
        viewModel { DataViewModel(get()) }
        viewModel { FoodViewModel(get()) }
    }
}
