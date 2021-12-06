package com.example.mustfitmvvmjetpack.repo.network

import com.example.mustfitmvvmjetpack.model.FoodApiModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("food-database/v2/parser?app_id=a003558e&app_key=e7d27a4853f6ae44181583be6dd1f7e2")
    fun getFoods(@Query("ingr") searchText: String): Single<FoodApiModel>
}