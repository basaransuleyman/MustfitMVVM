package com.example.mustfitmvvmjetpack.ui.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.ui.adapter.FoodAdapter
import com.example.mustfitmvvmjetpack.databinding.FragmentRecipesBinding
import com.example.mustfitmvvmjetpack.viewmodel.FoodViewModel
import org.koin.android.ext.android.inject
import java.util.*

class RecipesFragment : Fragment() {

    private lateinit var foodAdapter: FoodAdapter
    private val _viewModel by inject<FoodViewModel>()
    private lateinit var _binding: FragmentRecipesBinding
    private lateinit var _getPreferences: SharedPreferences
    private lateinit var _setPreferences: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @Suppress("NAME_SHADOWING")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        foodAdapter = FoodAdapter(arrayListOf())
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        initializeSharedPreferences()
        bindingDefaultValue()
        bindingRecyclerView()
        observeLiveData()
        bottomTabNavigation()
        searchFood()
        return _binding.root
    }

    private fun initializeSharedPreferences() {
        _getPreferences = requireActivity().getSharedPreferences("pref", MODE_PRIVATE)
        _setPreferences = _getPreferences.edit()
    }

    private fun bindingDefaultValue() {
        val foodName = _getPreferences.getString("foodName", "")?.lowercase(Locale.getDefault())
        _binding.edSearchFood.setText(foodName)
        _viewModel.getData(foodName!!)
        refreshPage(foodName)
    }

    private fun bindingRecyclerView() {
        with(_binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = foodAdapter
        }
    }

    private fun refreshPage(foodName: String) {
        with(_binding) {
            srLayout.setOnRefreshListener {
                recyclerView.visibility = View.GONE
                tvError.visibility = View.GONE
                pbLoading.visibility = View.GONE

                val foodName =
                    _getPreferences.getString("foodName", foodName)?.lowercase(Locale.getDefault())
                edSearchFood.setText(foodName)
                _viewModel.getData(foodName!!)
                srLayout.isRefreshing = false
            }
        }
    }

    private fun searchFood() {
        _binding.ivSearchIcon.setOnClickListener {
            val foodName = _binding.edSearchFood.text.toString()
            _setPreferences.putString("foodName", foodName)
            _setPreferences.apply()
            _viewModel.getData(foodName)
            observeLiveData()
        }
    }

    private fun bottomTabNavigation() {
        _binding.cnbTabNav.setOnItemSelectedListener {
            when (it) {
                R.id.recipes -> Navigation.findNavController(_binding.root)
                    .navigate(RecipesFragmentDirections.actionRecipesFragmentSelf())
                R.id.profile -> Navigation.findNavController(_binding.root)
                    .navigate(RecipesFragmentDirections.actionRecipesFragmentToProfileFragment())
                R.id.settings -> Navigation.findNavController(_binding.root)
                    .navigate(RecipesFragmentDirections.actionRecipesFragmentToOptionFragment())
            }
        }
    }

    private fun observeLiveData() {
        with(_viewModel) {
            foodsData.observe(viewLifecycleOwner, { foods ->
                foods?.let {
                    _binding.recyclerView.visibility = View.VISIBLE
                    foodAdapter.updateFoodWhenRefresh(foods)
                }
            })

            foodsError.observe(viewLifecycleOwner, { errorMessage ->
                errorMessage?.let {
                    if (it.isNotEmpty()) {
                        _binding.tvError.visibility = View.VISIBLE
                        _binding.tvError.text = errorMessage
                    } else {
                        _binding.tvError.visibility = View.GONE
                    }
                }
            })

            foodsLoading.observe(viewLifecycleOwner, { loading ->
                loading?.let {
                    if (it) {
                        _binding.pbLoading.visibility = View.VISIBLE
                        _binding.recyclerView.visibility = View.GONE
                        _binding.tvError.visibility = View.GONE
                    } else {
                        _binding.pbLoading.visibility = View.GONE
                    }
                }
            })
        }
    }
}





