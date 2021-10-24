package com.example.mustfitmvvmjetpack.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.databinding.FragmentProfileBinding
import com.example.mustfitmvvmjetpack.model.MustfitModel
import com.example.mustfitmvvmjetpack.viewmodel.AuthenticationViewModel
import com.example.mustfitmvvmjetpack.viewmodel.DataViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.math.log

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val informationArrayList: ArrayList<MustfitModel> = ArrayList()
    var adapter: MustfitRecyclerAdapter? = null
    private lateinit var viewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.rvProfile.layoutManager = LinearLayoutManager(activity)
        adapter = MustfitRecyclerAdapter(informationArrayList)
        binding.rvProfile.adapter = adapter

        bottomTabNavigation()
        viewModel.getDataFromDB()

        return binding.root
    }

    private fun bottomTabNavigation() {
        binding.cnbTabNav.setOnItemSelectedListener {
            when (it) {
                R.id.recipes -> Navigation.findNavController(binding.root)
                    .navigate(ProfileFragmentDirections.actionProfileFragmentToRecipesFragment())
                R.id.profile -> Navigation.findNavController(binding.root)
                    .navigate(ProfileFragmentDirections.actionProfileFragmentSelf())
                R.id.settings -> Navigation.findNavController(binding.root)
                    .navigate(ProfileFragmentDirections.actionProfileFragmentToOptionFragment())
            }
        }
    }

}