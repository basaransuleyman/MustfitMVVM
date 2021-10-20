package com.example.mustfitmvvmjetpack.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mustfitmvvmjetpack.databinding.FragmentProfileBinding
import com.example.mustfitmvvmjetpack.model.MustfitModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: FragmentProfileBinding
    private val informationArrayList: ArrayList<MustfitModel> = ArrayList()
    var adapter: MustfitRecyclerAdapter? = null

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

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        binding.rvProfile.layoutManager = LinearLayoutManager(activity)
        adapter = MustfitRecyclerAdapter(informationArrayList)
        binding.rvProfile.adapter = adapter

        db.collection("informations").orderBy(
            "userEmail", Query.Direction.DESCENDING
        ).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Toast.makeText(activity, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            } else {
                if (snapshot != null) {
                    if (!snapshot.isEmpty) {
                        informationArrayList.clear()

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
                            informationArrayList.add(post)
                        }
                        adapter!!.notifyDataSetChanged()
                    }
                }
            }
        }
        return binding.root
    }
}