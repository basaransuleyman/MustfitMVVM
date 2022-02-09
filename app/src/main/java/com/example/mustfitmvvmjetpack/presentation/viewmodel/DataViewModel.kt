package com.example.mustfitmvvmjetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mustfitmvvmjetpack.repo.DataRepository
import com.example.mustfitmvvmjetpack.utils.MathFunctionRequest
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var _appRepository: DataRepository
    private lateinit var _userMutableLiveData: MutableLiveData<FirebaseUser>
    private var _fireStoreDB = Firebase.firestore
    private var _messageLiveData = MutableLiveData<String>()

    fun mathFunction(request: MathFunctionRequest, isMale: Boolean?) {
        if (isMale != null) {
            _appRepository = DataRepository()
            if (isMale) {
                _appRepository.mathFunctionMan(request) { postMap ->
                    onMathFunctionCompleted(postMap)
                }
            } else {
                _appRepository.mathFunctionWoman(request) { postMap ->
                    onMathFunctionCompleted(postMap)
                }
            }
        } else {
            _messageLiveData.postValue("Error")
        }
    }

    private fun onMathFunctionCompleted(postMap: HashMap<String, Any>) {
        _fireStoreDB.collection("informations").add(postMap).addOnCompleteListener { task ->
            if (task.isComplete && task.isSuccessful) {
                _messageLiveData.postValue("Success")
            }
        }.addOnFailureListener { exception ->
            _messageLiveData.postValue("Error ")
        }
    }

    fun getDataFromDB() {
        _appRepository = DataRepository()
        _appRepository.getDataFromDB()
    }

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return _userMutableLiveData
    }
}