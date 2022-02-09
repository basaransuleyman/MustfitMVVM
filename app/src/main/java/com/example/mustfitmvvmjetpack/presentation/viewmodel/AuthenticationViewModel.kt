package com.example.mustfitmvvmjetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mustfitmvvmjetpack.repo.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var _appRepository: AuthenticationRepository
    private lateinit var _userLiveData: MutableLiveData<FirebaseUser>
    private lateinit var _loggedOutLiveData: MutableLiveData<Boolean>

    fun register(email: String, password: String) {
        _appRepository = AuthenticationRepository()
        _appRepository.register(email = email, password = password)
    }

    fun login(email: String, password: String) {
        _appRepository = AuthenticationRepository()
        _appRepository.login(email = email, password = password)
    }

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return _userLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<Boolean> {
        return _loggedOutLiveData
    }
}
