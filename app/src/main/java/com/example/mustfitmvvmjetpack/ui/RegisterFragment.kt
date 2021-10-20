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
import com.example.mustfitmvvmjetpack.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentRegisterBinding

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

        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        binding.signupButton.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            val password = binding.etRegisterPassword.text.toString()

            if (email == "" || password == "") {
                Toast.makeText(
                    activity, "Email and password must not be empty",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    Toast.makeText(
                        activity, "Success Registerion",
                        Toast.LENGTH_LONG
                    ).show()
                }.addOnFailureListener {
                    Toast.makeText(
                        activity, it.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.tvGoLogin.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
        return binding.root
    }
}
