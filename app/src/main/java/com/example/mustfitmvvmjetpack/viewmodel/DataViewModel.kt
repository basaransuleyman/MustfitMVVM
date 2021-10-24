package com.example.mustfitmvvmjetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mustfitmvvmjetpack.repo.DataRepository
import com.google.firebase.auth.FirebaseUser

class DataViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var appRepository: DataRepository
    private lateinit var userMutableLiveData: MutableLiveData<FirebaseUser>

    fun mathFunctionMan(
        etName: String,
        etHip: Int,
        etWaist: Int,
        etNeck: Int,
        etWeight: Double,
        etHeight: Double,
        etAge: Double,
        cbLoseWeight: Boolean,
        cbMaintainWeight: Boolean,
        cbGainWeight: Boolean,
        cbZeroDay: Boolean,
        cbOneTwoDay: Boolean,
        cbThreeFourDay: Boolean,
        cbFiveSixDay: Boolean,
        cbSevenDay: Boolean
    ) {
        appRepository = DataRepository()
        appRepository.mathFunctionMan(
            etName = etName,
            etHip = etHip,
            etWaist = etWaist,
            etNeck = etNeck,
            etWeight = etWeight,
            etHeight = etHeight,
            etAge = etAge,
            cbLoseWeight = cbLoseWeight,
            cbMaintainWeight = cbMaintainWeight,
            cbGainWeight = cbGainWeight,
            cbZeroDay = cbZeroDay,
            cbOneTwoDay = cbOneTwoDay,
            cbThreeFourDay = cbThreeFourDay,
            cbFiveSixDay = cbFiveSixDay,
            cbSevenDay = cbSevenDay
        )
    }

    fun mathFunctionWoman(
        etName: String,
        etHip: Int,
        etWaist: Int,
        etNeck: Int,
        etWeight: Double,
        etHeight: Double,
        etAge: Double,
        cbLoseWeight: Boolean,
        cbMaintainWeight: Boolean,
        cbGainWeight: Boolean,
        cbZeroDay: Boolean,
        cbOneTwoDay: Boolean,
        cbThreeFourDay: Boolean,
        cbFiveSixDay: Boolean,
        cbSevenDay: Boolean
    ){
        appRepository = DataRepository()
        appRepository.mathFunctionWoman(
            etName = etName,
            etHip = etHip,
            etWaist = etWaist,
            etNeck = etNeck,
            etWeight = etWeight,
            etHeight = etHeight,
            etAge = etAge,
            cbLoseWeight = cbLoseWeight,
            cbMaintainWeight = cbMaintainWeight,
            cbGainWeight = cbGainWeight,
            cbZeroDay = cbZeroDay,
            cbOneTwoDay = cbOneTwoDay,
            cbThreeFourDay = cbThreeFourDay,
            cbFiveSixDay = cbFiveSixDay,
            cbSevenDay = cbSevenDay
        )
    }

    fun getDataFromDB(){
        appRepository = DataRepository()
        appRepository.getDataFromDB()
    }

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }

}