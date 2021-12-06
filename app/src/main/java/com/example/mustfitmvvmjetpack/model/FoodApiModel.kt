package com.example.mustfitmvvmjetpack.model

import com.google.gson.annotations.SerializedName

data class FoodApiModel(
    @SerializedName("parsed")
    val hints: List<Hint>,

    @SerializedName("text")
    val searchText: String? = null
)