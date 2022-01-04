package com.example.mustfitmvvmjetpack

import com.example.mustfitmvvmjetpack.repo.DataRepository
import com.example.mustfitmvvmjetpack.utils.MathFunctionRequest
import org.junit.After
import org.junit.Before
import org.junit.Test

class DataRepositoryTest {

    private lateinit var dataRepositoryManMathFunction : DataRepository

    @Before// test oluşturulmadan önce yapılacak işlemler
    fun setup(){ // genelde adı setup
        dataRepositoryManMathFunction = DataRepository() // initiliaze işlemi
    }

    @After// room ile ilgili bir işlem db acıldıktan sonra kapama veya coroutine bittikten sonra
    fun tearDown(){

    }

    @Test
    fun mathFunctionManTest() {

    }

}