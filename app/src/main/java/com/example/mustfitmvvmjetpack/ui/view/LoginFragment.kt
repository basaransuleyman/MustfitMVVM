package com.example.mustfitmvvmjetpack.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.databinding.FragmentLoginBinding
import com.example.mustfitmvvmjetpack.viewmodel.AuthenticationViewModel
import org.koin.android.ext.android.inject


class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    private val _viewModel by inject<AuthenticationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginClickedOnLoginPage()
        registerClickedOnLoginPage()
        return _binding.root
    }

    private fun loginClickedOnLoginPage() {
        _binding.loginButton.setOnClickListener {
            _viewModel.login(
                email = _binding.etLoginEmail.text.toString(),
                password = _binding.etLoginPassword.text.toString()
            )
            Navigation.findNavController(it)
                .navigate(LoginFragmentDirections.actionLoginFragmentToInformationFragment())
        }
    }

    private fun registerClickedOnLoginPage() {
        _binding.tvGoRegister.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

    }
}