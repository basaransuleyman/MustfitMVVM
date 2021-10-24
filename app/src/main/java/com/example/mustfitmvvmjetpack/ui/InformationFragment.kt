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
import androidx.lifecycle.ViewModelProvider
import com.example.mustfitmvvmjetpack.viewmodel.DataViewModel


open class InformationFragment : Fragment() {

    var isMale: Boolean? = null
    private lateinit var binding: FragmentInformationBinding
    private lateinit var viewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInformationBinding.inflate(inflater, container, false)
        selectGender()
        return binding.root
    }

    private fun selectGender() {

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
                    viewModel.mathFunctionMan(
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
                        cbSevenDay = binding.cbSevenDay.isChecked
                    )
                    Navigation.findNavController(it)
                        .navigate(InformationFragmentDirections.actionInformationFragmentToProfileFragment())
                }
                false -> {
                    viewModel.mathFunctionWoman(
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
                        cbSevenDay = binding.cbSevenDay.isChecked
                    )
                    Navigation.findNavController(it)
                        .navigate(InformationFragmentDirections.actionInformationFragmentToProfileFragment())
                }
                else -> Toast.makeText(activity, "Gender must be selected", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}

