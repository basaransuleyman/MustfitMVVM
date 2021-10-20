package com.example.mustfitmvvmjetpack.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.databinding.FragmentInformationBinding
import com.example.mustfitmvvmjetpack.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            if (email == "" || password == "") {
                Toast.makeText(
                    activity, "Email and password must not be empty",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    Navigation.findNavController(binding.root)
                        .navigate(LoginFragmentDirections.actionLoginFragmentToInformationFragment())
                }.addOnFailureListener {
                    Toast.makeText(
                        activity, it.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.tvGoRegister.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        return binding.root
    }

}