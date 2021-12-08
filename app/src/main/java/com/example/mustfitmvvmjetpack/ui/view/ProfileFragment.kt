package com.example.mustfitmvvmjetpack.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.ui.adapter.MustfitRecyclerAdapter
import com.example.mustfitmvvmjetpack.databinding.FragmentProfileBinding
import com.example.mustfitmvvmjetpack.model.MustfitModel
import com.example.mustfitmvvmjetpack.viewmodel.DataViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.inject


class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentProfileBinding
    private val _informationArrayList: ArrayList<MustfitModel> = ArrayList()
    private var _adapter: MustfitRecyclerAdapter? = null
    private val _viewModel by inject<DataViewModel>()
    private lateinit var _firebaseAuth: FirebaseAuth
    private lateinit var _fireStoreDB: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        //viewModel.getDataFromDB()
        bindingRecyclerView()
        getDataFromDB()
        bottomTabNavigation()
        return _binding.root
    }

    private fun bindingRecyclerView(){
        _binding.rvProfile.layoutManager = LinearLayoutManager(activity)
        _adapter = MustfitRecyclerAdapter(_informationArrayList)
        _binding.rvProfile.adapter = _adapter
    }

    private fun bottomTabNavigation() {
        _binding.cnbTabNav.setOnItemSelectedListener {
            when (it) {
                R.id.recipes -> Navigation.findNavController(_binding.root)
                    .navigate(ProfileFragmentDirections.actionProfileFragmentToRecipesFragment())
                R.id.profile -> Navigation.findNavController(_binding.root)
                    .navigate(ProfileFragmentDirections.actionProfileFragmentSelf())
                R.id.settings -> Navigation.findNavController(_binding.root)
                    .navigate(ProfileFragmentDirections.actionProfileFragmentToOptionFragment())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFromDB() {
        _firebaseAuth = Firebase.auth
        _fireStoreDB = Firebase.firestore
        _fireStoreDB.collection("informations").orderBy(
            "userEmail", com.google.firebase.firestore.Query.Direction.DESCENDING
        ).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
            } else {
                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        _informationArrayList.clear()
                        val documents = snapshot.documents
                        for (document in documents) {
                            val name = document.get("name") as String?
                            val bodyFat = document.get("bodyFat") as String
                            val dealWeight = document.get("dealWeight") as String
                            val messageBodyFat = document.get("message") as String
                            val messageLoseGainWeight = document.get("messagetwo") as String
                            val messageCalorie = document.get("messageone") as String
                            val post = MustfitModel(
                                name,
                                bodyFat,
                                dealWeight,
                                messageBodyFat,
                                messageLoseGainWeight,
                                messageCalorie
                            )
                            _informationArrayList.add(post)
                        }
                        _adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}