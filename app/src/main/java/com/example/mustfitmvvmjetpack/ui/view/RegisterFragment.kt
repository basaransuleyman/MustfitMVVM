package com.example.mustfitmvvmjetpack.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.databinding.FragmentRegisterBinding
import com.example.mustfitmvvmjetpack.viewmodel.AuthenticationViewModel
import org.koin.android.ext.android.inject

class RegisterFragment : Fragment() {

    private lateinit var _binding: FragmentRegisterBinding
    private val _viewModel by inject<AuthenticationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        registerClickedOnRegisterPage()
        loginClickedOnRegisterPage()
        return _binding.root
    }

    private fun registerClickedOnRegisterPage() {
        _binding.signupButton.setOnClickListener {
            _viewModel.register(
                email = _binding.etRegisterEmail.text.toString(),
                password = _binding.etRegisterPassword.text.toString()
            )
        }
    }

    private fun loginClickedOnRegisterPage() {
        _binding.tvGoLogin.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }
}
