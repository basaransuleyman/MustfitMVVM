package com.example.mustfitmvvmjetpack.ui.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.databinding.FragmentInformationBinding
import com.example.mustfitmvvmjetpack.utils.MathFunctionRequest
import com.example.mustfitmvvmjetpack.viewmodel.DataViewModel
import org.koin.android.ext.android.inject

class InformationFragment : Fragment() {

    private var _isMale: Boolean? = null
    private lateinit var _binding: FragmentInformationBinding
    private val _viewModel by inject<DataViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)

        selectGender()
        return _binding.root
    }

    private fun selectGender() {
        _binding.ivWoman.setOnClickListener {
            _isMale = false
            _binding.ivMan.setBackgroundColor(Color.TRANSPARENT)
            _binding.ivWoman.setBackgroundColor(Color.YELLOW)
        }
        _binding.ivMan.setOnClickListener {
            _isMale = true
            _binding.ivWoman.setBackgroundColor(Color.TRANSPARENT)
            _binding.ivMan.setBackgroundColor(Color.YELLOW)
        }
        _binding.nextButton.setOnClickListener {
            _viewModel.mathFunction(request = helperMathRequest(), _isMale)
            Navigation.findNavController(it)
                .navigate(InformationFragmentDirections.actionInformationFragmentToProfileFragment())
        }
    }

    private fun helperMathRequest(): MathFunctionRequest {
        return MathFunctionRequest(
            etName = _binding.etName.text.toString(),
            etHip = _binding.etHip.text.toString().toInt(),
            etWaist = _binding.etWaist.text.toString().toInt(),
            etNeck = _binding.etNeck.text.toString().toInt(),
            etHeight = _binding.etHeight.text.toString().toDouble(),
            etWeight = _binding.etWeight.text.toString().toDouble(),
            etAge = _binding.etAge.text.toString().toDouble(),
            cbLoseWeight = _binding.cbLoseWeight.isChecked,
            cbMaintainWeight = _binding.cbMaintainWeight.isChecked,
            cbGainWeight = _binding.cbGainWeight.isChecked,
            cbZeroDay = _binding.cbZeroDay.isChecked,
            cbOneTwoDay = _binding.cbOneTwoDay.isChecked,
            cbThreeFourDay = _binding.cbThreeFourDay.isChecked,
            cbFiveSixDay = _binding.cbFiveSixDay.isChecked,
            cbSevenDay = _binding.cbSevenDay.isChecked
        )
    }
}

