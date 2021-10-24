package com.example.mustfitmvvmjetpack.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.databinding.ActivityInformationBinding.inflate
import com.example.mustfitmvvmjetpack.databinding.FragmentProfileBinding
import com.example.mustfitmvvmjetpack.databinding.FragmentRecipesBinding
import com.example.mustfitmvvmjetpack.databinding.FragmentRegisterBinding

class RecipesFragment : Fragment() {
        private  lateinit var  binding : FragmentRecipesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        bottomTabNavigation()
        return binding.root
    }

    private fun bottomTabNavigation() {
        binding.cnbTabNav.setOnItemSelectedListener {
            when (it) {
                R.id.recipes -> Navigation.findNavController(binding.root)
                    .navigate(RecipesFragmentDirections.actionRecipesFragmentSelf())
                R.id.profile -> Navigation.findNavController(binding.root)
                    .navigate(RecipesFragmentDirections.actionRecipesFragmentToProfileFragment())
                R.id.settings -> Navigation.findNavController(binding.root)
                    .navigate(RecipesFragmentDirections.actionRecipesFragmentToOptionFragment())
            }
        }
    }
}