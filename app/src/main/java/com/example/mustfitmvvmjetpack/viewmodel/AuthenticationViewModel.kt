package com.example.mustfitmvvmjetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mustfitmvvmjetpack.repo.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var appRepository: AuthenticationRepository
    private lateinit var userMutableLiveData: MutableLiveData<FirebaseUser>
    private lateinit var loggedOutLiveData: MutableLiveData<Boolean>


    fun register(email: String, password: String) {
        appRepository = AuthenticationRepository()
        appRepository.register(email = email, password = password)
    }

    fun login(email: String, password: String) {
        appRepository = AuthenticationRepository()
        appRepository.login(email = email, password = password)
    }

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<Boolean> {
        return loggedOutLiveData
    }
}
