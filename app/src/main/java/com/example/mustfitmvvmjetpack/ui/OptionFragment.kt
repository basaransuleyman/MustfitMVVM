package com.example.mustfitmvvmjetpack.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.databinding.FragmentOptionBinding
import com.example.mustfitmvvmjetpack.databinding.FragmentRecipesBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionFragment : Fragment() {
    private  lateinit var  binding : FragmentOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOptionBinding.inflate(inflater, container, false)
        bottomTabNavigation()
        return binding.root
    }

    private fun bottomTabNavigation() {
        binding.cnbTabNav.setOnItemSelectedListener {
            when (it) {
                R.id.recipes -> Navigation.findNavController(binding.root)
                    .navigate(OptionFragmentDirections.actionOptionFragmentToRecipesFragment())
                R.id.profile -> Navigation.findNavController(binding.root)
                    .navigate(OptionFragmentDirections.actionOptionFragmentToProfileFragment())
                R.id.settings -> Navigation.findNavController(binding.root)
                    .navigate(OptionFragmentDirections.actionOptionFragmentSelf())
            }
        }
    }
}