package com.example.mustfitmvvmjetpack.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.databinding.FragmentOptionBinding

class OptionFragment : Fragment() {

    private lateinit var _binding: FragmentOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptionBinding.inflate(inflater, container, false)
        bottomTabNavigation()
        return _binding.root
    }

    private fun bottomTabNavigation() {
        _binding.cnbTabNav.setOnItemSelectedListener {
            when (it) {
                R.id.recipes -> Navigation.findNavController(_binding.root)
                    .navigate(OptionFragmentDirections.actionOptionFragmentToRecipesFragment())
                R.id.profile -> Navigation.findNavController(_binding.root)
                    .navigate(OptionFragmentDirections.actionOptionFragmentToProfileFragment())
                R.id.settings -> Navigation.findNavController(_binding.root)
                    .navigate(OptionFragmentDirections.actionOptionFragmentSelf())
            }
        }
    }
}