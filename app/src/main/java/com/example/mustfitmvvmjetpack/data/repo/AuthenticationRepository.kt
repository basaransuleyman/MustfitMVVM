package com.example.mustfitmvvmjetpack.data.repo

import android.app.Application

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository {

    private lateinit var _application: Application
    private lateinit var _firebaseAuth: FirebaseAuth
    private lateinit var _userLiveData: MutableLiveData<FirebaseUser>
    private lateinit var _loggedOutLiveData: MutableLiveData<FirebaseUser>
    private lateinit var _messageLiveData: MutableLiveData<String>
    /*  if (firebaseAuth.currentUser != null) {
        userMutableLiveData.postValue(firebaseAuth.currentUser)
        loggedOutLiveData.postValue(firebaseAuth.currentUser)
    } */

    fun register(email: String, password: String) {
        _firebaseAuth = FirebaseAuth.getInstance()
        _application = Application()
        _userLiveData = MutableLiveData()
        _messageLiveData = MutableLiveData<String>()

        if (email == "" || password == "") {
            _messageLiveData.postValue("Email and password must not be empty")
        } else {
            _firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _userLiveData.postValue(_firebaseAuth.currentUser)
                        _messageLiveData.postValue("Register Success")
                    } else {
                        _messageLiveData.postValue("Register Failed")
                    }
                }
        }
    }

    fun login(email: String, password: String) {
        _firebaseAuth = FirebaseAuth.getInstance()
        _application = Application()
        _userLiveData = MutableLiveData()
        _messageLiveData = MutableLiveData<String>()

        if (email == "" || password == "") {
            _messageLiveData.postValue("Email and password must not be empty")
        } else {
            _firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _userLiveData.postValue(_firebaseAuth.currentUser)
                    } else {
                        _messageLiveData.postValue("Register Failed")
                    }
                }
        }
    }

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return _userLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<FirebaseUser> {
        return _loggedOutLiveData
    }

    /*
    fun logOut() {
        firebaseAuth.signOut()
        loggedOutLiveData.postValue(true)
    }
    */
}