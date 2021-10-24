package com.example.mustfitmvvmjetpack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.databinding.ActivityMainBinding
import com.example.mustfitmvvmjetpack.databinding.FragmentInformationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}