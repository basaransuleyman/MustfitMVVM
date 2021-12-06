package com.example.mustfitmvvmjetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mustfitmvvmjetpack.model.FoodApiModel
import com.example.mustfitmvvmjetpack.repo.network.FoodApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val _foodApiService = FoodApiService()
    private val _disposable = CompositeDisposable()

    val foodsData = MutableLiveData<List<FoodApiModel>>()
    val foodsError = MutableLiveData<String>()
    val foodsLoading = MutableLiveData<Boolean>()

    fun getData(searchText: String) {
        getDataFromAPI(searchText)
    }

    private fun getDataFromAPI(searchText: String) {
        foodsLoading.value = true
        val liveData = MutableLiveData<String>()
        _disposable.add(
            _foodApiService.getData(searchText)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<FoodApiModel>() {

                    override fun onSuccess(t: FoodApiModel) {
                        foodsData.value = listOf(t)
                        foodsError.value = ""
                        foodsLoading.value = false
                        liveData.postValue("Success")
                    }

                    override fun onError(e: Throwable) {
                        foodsError.value = e.message
                        foodsLoading.value = false
                    }
                })
        )
    }
}