package com.example.mustfitmvvmjetpack.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.databinding.FragmentInformationBinding
import com.example.mustfitmvvmjetpack.ui.math.MathFunctionMan
import com.example.mustfitmvvmjetpack.ui.math.MathFunctionWoman
import android.content.Intent
import android.app.Activity


open class InformationFragment : Fragment() {

    var isMale: Boolean? = null
    private lateinit var binding: FragmentInformationBinding

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

        val mathFunctionMan = MathFunctionMan() // view model ' e çek
        val mathFunctionWoman = MathFunctionWoman()
        binding = FragmentInformationBinding.inflate(inflater, container, false)

        binding.ivWoman.setOnClickListener {
            isMale = false
            binding.ivMan.setBackgroundColor(Color.TRANSPARENT)
            binding.ivWoman.setBackgroundColor(Color.YELLOW)
        }
        binding.ivMan.setOnClickListener {
            isMale = true
            binding.ivWoman.setBackgroundColor(Color.TRANSPARENT)
            binding.ivMan.setBackgroundColor(Color.YELLOW)
        }

        binding.nextButton.setOnClickListener {
            when (isMale) {
                true -> {
                    mathFunctionMan.mathFunctionMan(
                        inflater,
                        container,
                        etName = binding.etName.text.toString(),
                        etHip = binding.etHip.text.toString().toInt(),
                        etWaist = binding.etWaist.text.toString().toInt(),
                        etNeck = binding.etNeck.text.toString().toInt(),
                        etHeight = binding.etHeight.text.toString().toDouble(),
                        etWeight = binding.etWeight.text.toString().toDouble(),
                        etAge = binding.etAge.text.toString().toDouble(),
                        cbLoseWeight = binding.cbLoseWeight.isChecked,
                        cbMaintainWeight = binding.cbMaintainWeight.isChecked,
                        cbGainWeight = binding.cbGainWeight.isChecked,
                        cbZeroDay = binding.cbZeroDay.isChecked,
                        cbOneTwoDay = binding.cbOneTwoDay.isChecked,
                        cbThreeFourDay = binding.cbThreeFourDay.isChecked,
                        cbFiveSixDay = binding.cbFiveSixDay.isChecked,
                        cbSevenDay = binding.cbSevenDay.isChecked,
                    )
                    Navigation.findNavController(it)
                        .navigate(InformationFragmentDirections.actionInformationFragmentToProfileFragment())
                }
                false -> {
                    mathFunctionWoman.mathFunctionWoman(inflater = inflater, container = container)
                }
                else -> Toast.makeText(activity, "Gender must be selected", Toast.LENGTH_LONG)
                    .show()
            }
        }
        return binding.root
    }
}

