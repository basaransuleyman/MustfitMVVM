package com.example.mustfitmvvmjetpack.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.databinding.FragmentLoginBinding
import com.example.mustfitmvvmjetpack.viewmodel.AuthenticationViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginClickedOnLoginPage()
        registerClickedOnLoginPage()
        return binding.root
    }

    private fun loginClickedOnLoginPage() {
        binding.loginButton.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()
            viewModel.login(email=email, password=password)
            Navigation.findNavController(it)
                .navigate(LoginFragmentDirections.actionLoginFragmentToInformationFragment())
        }
    }

    private fun registerClickedOnLoginPage() {
        binding.tvGoRegister.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

    }
}