package com.example.mustfitmvvmjetpack

import android.app.Application
import com.example.mustfitmvvmjetpack.di.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        insertKoin()
    }

    private fun insertKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(KoinModule.viewModelModule))
        }
    }
}