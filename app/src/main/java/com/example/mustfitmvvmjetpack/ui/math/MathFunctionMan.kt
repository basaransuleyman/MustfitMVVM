package com.example.mustfitmvvmjetpack.ui.math

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.mustfitmvvmjetpack.databinding.FragmentInformationBinding
import com.example.mustfitmvvmjetpack.ui.InformationFragment
import com.example.mustfitmvvmjetpack.ui.InformationFragmentDirections
import com.example.mustfitmvvmjetpack.ui.ProfileFragment
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_information.*
import kotlin.math.log
import kotlin.math.log10
import kotlin.math.round
import kotlin.math.roundToInt

class MathFunctionMan() : InformationFragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    fun mathFunctionMan(
        inflater: LayoutInflater,
        container: ViewGroup?,
        etName: String,
        etHip: Int,
        etWaist: Int,
        etNeck: Int,
        etWeight: Double,
        etHeight: Double,
        etAge: Double,
        cbLoseWeight: Boolean,
        cbMaintainWeight: Boolean,
        cbGainWeight: Boolean,
        cbZeroDay: Boolean,
        cbOneTwoDay: Boolean,
        cbThreeFourDay: Boolean,
        cbFiveSixDay: Boolean,
        cbSevenDay: Boolean
    ) {
        auth = Firebase.auth
        db = Firebase.firestore

        var message = " "
        var messagetwo = " "
        var messageone = " "

        val wn = (etHip * 0) + etWaist - etNeck
        val bodyFat = Math.round(
            495 / (1.0324 - 0.19077 * log10(wn.toDouble()) + 0.15456 * log10(etHeight)) - 450
        )
        val dealWeight = Math.round((2.3 * ((etHeight * 0.39370079) - 60) + 50))
        val bmr = Math.round(5 + (10 * etWeight) + (6.25 * etHeight) - (5 * etAge))

        val koruma1 = Math.round(1.2 * bmr)
        val koruma2 = Math.round(1.375 * bmr)
        val koruma3 = Math.round(1.55 * bmr)
        val koruma4 = Math.round(1.725 * bmr)
        val koruma5 = Math.round(1.9 * bmr)
        val alma1 = Math.round(1.32 * bmr)
        val alma2 = Math.round(1.512 * bmr)
        val alma3 = Math.round(1.704 * bmr)
        val alma4 = Math.round(1.897 * bmr)
        val alma5 = Math.round(2.090 * bmr)
        val verme1 = bmr
        val verme2 = Math.round(1.145 * bmr)
        val verme3 = Math.round(1.291 * bmr)
        val verme4 = Math.round(1.437 * bmr)
        val verme5 = Math.round(1.583 * bmr)

        when {
            bodyFat.toDouble() in 2.0..5.0 -> {
                message = "Body fat is Essential for life "
            }
            bodyFat.toDouble() in 6.0..13.0 -> {
                message = "Body fat is Athletes "
            }
            bodyFat.toDouble() in 14.0..17.0 -> {
                message = "Body fat is People in shape "
            }
            bodyFat.toDouble() in 18.0..24.0 -> {
                message = "Boody fat is Normal "
            }
            bodyFat.toDouble() >= 25.0 -> {
                message = "Body fat is Obese "
            }
            else -> {
                message = "Wrong body fat "
            }
        }

        if (cbZeroDay && cbLoseWeight) {
            messagetwo = "To lose weight need $verme1 cal/day  "
        } else if (cbZeroDay && cbMaintainWeight) {
            messagetwo = "To protect  weight need $koruma1 cal/day "
        } else if (cbZeroDay && cbMaintainWeight) {
            messagetwo = "To gain weight need $alma1 cal/day "
        } else if (cbOneTwoDay && cbLoseWeight) {
            messagetwo = "To lose weight need $verme2 cal/day "
        } else if (cbOneTwoDay && cbMaintainWeight) {
            messagetwo = "To protect weight need $koruma2 cal/day "
        } else if (cbOneTwoDay && cbGainWeight) {
            messagetwo = "To gain weight need $alma2 cal/day "
        } else if (cbThreeFourDay && cbLoseWeight) {
            messagetwo = "To lose weight need $verme3 cal/day "
        } else if (cbThreeFourDay && cbMaintainWeight) {
            messagetwo = "To lose weight need $koruma3 cal/day "
        } else if (cbThreeFourDay && cbGainWeight) {
            messagetwo = "To lose weight need $alma3 cal/day "
        } else if (cbFiveSixDay && cbLoseWeight) {
            messagetwo = "To lose weight need $verme4 cal/day "
        } else if (cbFiveSixDay && cbMaintainWeight) {
            messagetwo = "To protect weight need $koruma4 cal/day "
        } else if (cbFiveSixDay && cbGainWeight) {
            messagetwo = "To gain weight need $alma4 cal/day "
        } else if (cbSevenDay && cbLoseWeight) {
            messagetwo = "To lose weight need $verme5 cal/day "
        } else if (cbSevenDay && cbMaintainWeight) {
            messagetwo = "To protect weight need $koruma5 cal/day "
        } else if (cbSevenDay && cbGainWeight) {
            messagetwo = "To gain weight need $alma5 cal/day "
        }

        if (cbZeroDay && cbLoseWeight) {
            messageone =
                "Breakfast " + (verme1 / 3.332).roundToInt() + " calories \n" + "Lunch " + (verme1 / 2.500) + " calories \n" + "Dinner " + (verme1 / 4.00) + " calories \n" + "Snack " + Math.round(
                    (verme1 / 19.92)
                ) + " calories \n"
        } else if (cbZeroDay && cbMaintainWeight) {
            messageone =
                "Breakfast " + (koruma1 / 3.332).roundToInt() + " calories \n" + "Lunch " + (koruma1 / 2.500) + " calories \n" + "Dinner " + (koruma1 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma1 / 19.92)
                ) + " calories \n\n"
        } else if (cbZeroDay && cbMaintainWeight) {
            messageone =
                "Breakfast " + (alma1 / 3.332).roundToInt() + " calories \n" + "Lunch " + (alma1 / 2.500) + " calories \n\n" + "Dinner " + (alma1 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma1 / 19.92)
                ) + " calories \n\n"
        } else if (cbOneTwoDay && cbLoseWeight) {
            messageone =
                "Breakfast " + (verme2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme2 / 2.500) + " calories \n\n" + "Dinner " + (verme2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme2 / 19.92)
                ) + " calories \n\n"
        } else if (cbOneTwoDay && cbMaintainWeight) {
            messageone =
                "Breakfast " + (koruma2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma2 / 2.500) + " calories \n\n" + "Dinner " + (koruma2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma2 / 19.92)
                ) + " calories \n\n"
        } else if (cbOneTwoDay && cbGainWeight) {
            messageone =
                "Breakfast " + (alma2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma2 / 2.500) + " calories \n\n" + "Dinner " + (alma2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma2 / 19.92)
                ) + " calories \n\n"
        } else if (cbThreeFourDay && cbLoseWeight) {
            messageone =
                "Breakfast " + (verme3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme3 / 2.500) + " calories \n\n" + "Dinner " + (verme3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme3 / 19.92)
                ) + " calories \n\n"
        } else if (cbThreeFourDay && cbMaintainWeight) {
            messageone =
                "Breakfast " + (koruma3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma3 / 2.500) + " calories \n\n" + "Dinner " + (koruma3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma3 / 19.92)
                ) + " calories \n\n"
        } else if (cbThreeFourDay && cbGainWeight) {
            messageone =
                "Breakfast " + (alma3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma3 / 2.500) + " calories \n\n" + "Dinner " + (alma3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma3 / 19.92)
                ) + " calories \n\n"
        } else if (cbFiveSixDay && cbLoseWeight) {
            messageone =
                "Breakfast " + (verme4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme4 / 2.500) + " calories \n\n" + "Dinner " + (verme4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme4 / 19.92)
                ) + " calories \n\n"
        } else if (cbFiveSixDay && cbMaintainWeight) {
            messageone =
                "Breakfast " + (koruma4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma4 / 2.500) + " calories \n\n" + "Dinner " + (koruma4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma4 / 19.92)
                ) + " calories \n\n"
        } else if (cbFiveSixDay && cbGainWeight) {
            messageone =
                "Breakfast " + (alma4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma4 / 2.500) + " calories \n\n" + "Dinner " + (alma4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma4 / 19.92)
                ) + " calories \n\n"
        } else if (cbSevenDay && cbLoseWeight) {
            messageone =
                "Breakfast " + (verme5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme5 / 2.500) + " calories \n\n" + "Dinner " + (verme5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme5 / 19.92)
                ) + " calories \n\n"
        } else if (cbSevenDay && cbMaintainWeight) {
            messageone =
                "Breakfast " + (koruma5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma5 / 2.500) + " calories \n\n" + "Dinner " + (koruma5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma5 / 19.92)
                ) + " calories \n\n"
        } else if (cbSevenDay && cbGainWeight) {
            messageone =
                "Breakfast " + (alma5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma5 / 2.500) + " calories \n\n" + "Dinner " + (alma5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma5 / 19.92)
                ) + " calories \n\n"
        }

        val postMap = hashMapOf<String, Any>()
        postMap.put("userEmail", auth.currentUser!!.email.toString())
        postMap.put("name", etName)
        postMap.put("bodyFat", bodyFat.toString())
        postMap.put("dealWeight", dealWeight.toString())
        postMap.put("message", message)
        postMap.put("messagetwo", messagetwo)
        postMap.put("messageone", messageone)
        postMap.put("date", Timestamp.now())

        db.collection("informations").add(postMap).addOnCompleteListener { task ->
            if (task.isComplete && task.isSuccessful) {
                context?.let { context ->
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener { exception ->
            context?.let { context ->
                Toast.makeText(context, exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}