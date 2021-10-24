package com.example.mustfitmvvmjetpack.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.databinding.FragmentRegisterBinding
import com.example.mustfitmvvmjetpack.viewmodel.AuthenticationViewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        registerClickedOnRegisterPage()
        loginClickedOnRegisterPage()
        return binding.root
    }

    private fun registerClickedOnRegisterPage() {
        binding.signupButton.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            viewModel.register(email = email, password = password)
        }
    }

    private fun loginClickedOnRegisterPage() {
        binding.tvGoLogin.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }
}
