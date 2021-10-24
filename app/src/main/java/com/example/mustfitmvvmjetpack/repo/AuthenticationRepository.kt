package com.example.mustfitmvvmjetpack.repo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthenticationRepository {

    private lateinit var application: Application
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userMutableLiveData: MutableLiveData<FirebaseUser>
    private lateinit var loggedOutLiveData: MutableLiveData<FirebaseUser>

    /*  if (firebaseAuth.currentUser != null) {
        userMutableLiveData.postValue(firebaseAuth.currentUser)
        loggedOutLiveData.postValue(firebaseAuth.currentUser)
    } */
    fun register(email: String, password: String) {
        firebaseAuth = FirebaseAuth.getInstance()
        application = Application()
        userMutableLiveData = MutableLiveData()

        if (email == "" || password == "") {
            Toast.makeText(
                application, "Email and password must not be empty",
                Toast.LENGTH_LONG
            ).show()
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        userMutableLiveData.postValue(firebaseAuth.currentUser)
                        Toast.makeText(application, "Register Success", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(application, "Register Failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun login(email: String, password: String) {
        firebaseAuth = FirebaseAuth.getInstance()
        application = Application()
        userMutableLiveData = MutableLiveData()

        if (email == "" || password == "") {
            Toast.makeText(
                application, "Email and password must not be empty",
                Toast.LENGTH_LONG
            ).show()
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        userMutableLiveData.postValue(firebaseAuth.currentUser)
                    } else {
                        Toast.makeText(application, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<FirebaseUser> {
        return loggedOutLiveData
    }

    /*
    fun logOut() {
        firebaseAuth.signOut()
        loggedOutLiveData.postValue(true)
    }
    */
}