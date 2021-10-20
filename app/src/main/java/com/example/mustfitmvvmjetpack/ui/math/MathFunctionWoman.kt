package com.example.mustfitmvvmjetpack.ui.math

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.ui.InformationFragment
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_information.*
import java.lang.Math.*
import kotlin.math.log
import kotlin.math.roundToInt

class MathFunctionWoman() : InformationFragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    fun mathFunctionWoman(inflater: LayoutInflater, container: ViewGroup?) {

        val root = inflater.inflate(R.layout.fragment_information, container, false)
        val etHip: EditText = root.findViewById(R.id.etHip)
        val etWaist: EditText = root.findViewById(R.id.etWaist)
        val etNeck: EditText = root.findViewById(R.id.etNeck)
        val etHeight: EditText = root.findViewById(R.id.etHeight)
        val etAge: EditText = root.findViewById(R.id.etAge)
        val etWeight: EditText = root.findViewById(R.id.etWeight)

        auth = Firebase.auth
        db = Firebase.firestore

        var message = " "
        var messagetwo = " "
        var messageone = " "

        val wnh = etHip.text.trim().toString().toInt() + etWaist.text.trim().toString()
            .toInt() - etNeck.text.trim().toString().toInt()
        val bodyFat = (495 / (1.29579 - 0.35004 * log(10.0, wnh.toDouble()) + 0.22100 * log(
            10.0,
            etHeight.text.toString().toDouble()
        )) - 450).roundToInt()
        val bm = round(
            (10000 * etWeight.text.toString().toDouble()) / (etHeight.text.toString()
                .toDouble() * etHeight.text.toString().toDouble())
        ).toInt()
        val dealWeight =
            (2.3 * ((etHeight.text.toString().toDouble() * 0.39370079) - 60) + 45.5).roundToInt()
        val bmr = round(
            ((10 * etWeight.text.toString().toDouble()) + (6.25 * etHeight.text.toString()
                .toDouble()) - (5 * etAge.text.toString().toDouble()) - 161)
        ).toInt()

        val koruma1 = (1.2 * bmr).roundToInt()
        val koruma2 = (1.375 * bmr).roundToInt()
        val koruma3 = (1.55 * bmr).roundToInt()
        val koruma4 = (1.725 * bmr).roundToInt()
        val koruma5 = (1.9 * bmr).roundToInt()
        val alma1 = (1.32 * bmr).roundToInt()
        val alma2 = (1.512 * bmr).roundToInt()
        val alma3 = (1.704 * bmr).roundToInt()
        val alma4 = (1.897 * bmr).roundToInt()
        val alma5 = (2.090 * bmr).roundToInt()
        val verme1 = bmr
        val verme2 = (1.145 * bmr).roundToInt()
        val verme3 = (1.291 * bmr).roundToInt()
        val verme4 = (1.437 * bmr).roundToInt()
        val verme5 = (1.583 * bmr).roundToInt()

        when {
            bodyFat in 10..13 -> {
                message = "Body fat is Essential for life"
            }
            bodyFat in 14..20 -> {
                message = "Body fat is Athletes"
            }
            bodyFat in 21..24 -> {
                message = "Body fat is People in shape"
            }
            bodyFat in 25..31 -> {
                message = "Body fat is Normal"
            }
            bodyFat >= 32 -> {
                message = "Body fat is Obese"
            }
            else -> {
                message = "Body fat is Wrong body fat"
            }
        }

        if (cbZeroDay.isChecked && cbLoseWeight.isChecked) {
            messagetwo = "To lose weight need $verme1 cal/day  "
        } else if (cbZeroDay.isChecked && cbMaintainWeight.isChecked) {
            messagetwo = "To protect  weight need $koruma1 cal/day "
        } else if (cbZeroDay.isChecked && cbMaintainWeight.isChecked) {
            messagetwo = "To gain weight need $alma1 cal/day "
        } else if (cbOneTwoDay.isChecked && cbLoseWeight.isChecked) {
            messagetwo = "To lose weight need $verme2 cal/day "
        } else if (cbOneTwoDay.isChecked && cbMaintainWeight.isChecked) {
            messagetwo = "To protect weight need $koruma2 cal/day "
        } else if (cbOneTwoDay.isChecked && cbGainWeight.isChecked) {
            messagetwo = "To gain weight need $alma2 cal/day "
        } else if (cbThreeFourDay.isChecked && cbLoseWeight.isChecked) {
            messagetwo = "To lose weight need $verme3 cal/day "
        } else if (cbThreeFourDay.isChecked && cbMaintainWeight.isChecked) {
            messagetwo = "To lose weight need $koruma3 cal/day "
        } else if (cbThreeFourDay.isChecked && cbGainWeight.isChecked) {
            messagetwo = "To lose weight need $alma3 cal/day "
        } else if (cbFiveSixDay.isChecked && cbLoseWeight.isChecked) {
            messagetwo = "To lose weight need $verme4 cal/day "
        } else if (cbFiveSixDay.isChecked && cbMaintainWeight.isChecked) {
            messagetwo = "To protect weight need $koruma4 cal/day "
        } else if (cbFiveSixDay.isChecked && cbGainWeight.isChecked) {
            messagetwo = "To gain weight need $alma4 cal/day "
        } else if (cbSevenDay.isChecked && cbLoseWeight.isChecked) {
            messagetwo = "To lose weight need $verme5 cal/day "
        } else if (cbSevenDay.isChecked && cbMaintainWeight.isChecked) {
            messagetwo = "To protect weight need $koruma5 cal/day "
        } else if (cbSevenDay.isChecked && cbGainWeight.isChecked) {
            messagetwo = "To gain weight need $alma5 cal/day "
        }

        if (cbZeroDay.isChecked && cbLoseWeight.isChecked) {
            messageone =
                "Breakfast " + (verme1 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme1 / 2.500) + " calories \n\n" + "Dinner " + (verme1 / 4.00) + " calories \n\n" + "Snack " + round(
                    (verme1 / 19.92)
                ) + " calories \n\n"
        } else if (cbZeroDay.isChecked && cbMaintainWeight.isChecked) {
            messageone =
                "Breakfast " + (koruma1 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma1 / 2.500) + " calories \n\n" + "Dinner " + (koruma1 / 4.00) + " calories \n\n" + "Snack " + round(
                    (koruma1 / 19.92)
                ) + " calories \n\n"
        } else if (cbZeroDay.isChecked && cbMaintainWeight.isChecked) {
            messageone =
                "Breakfast " + (alma1 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma1 / 2.500) + " calories \n\n" + "Dinner " + (alma1 / 4.00) + " calories \n\n" + "Snack " + round(
                    (alma1 / 19.92)
                ) + " calories \n\n"
        } else if (cbOneTwoDay.isChecked && cbLoseWeight.isChecked) {
            messageone =
                "Breakfast " + (verme2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme2 / 2.500) + " calories \n\n" + "Dinner " + (verme2 / 4.00) + " calories \n\n" + "Snack " + round(
                    (verme2 / 19.92)
                ) + " calories \n\n"
        } else if (cbOneTwoDay.isChecked && cbMaintainWeight.isChecked) {
            messageone =
                "Breakfast " + (koruma2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma2 / 2.500) + " calories \n\n" + "Dinner " + (koruma2 / 4.00) + " calories \n\n" + "Snack " + round(
                    (koruma2 / 19.92)
                ) + " calories \n\n"
        } else if (cbOneTwoDay.isChecked && cbGainWeight.isChecked) {
            messageone =
                "Breakfast " + (alma2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma2 / 2.500) + " calories \n\n" + "Dinner " + (alma2 / 4.00) + " calories \n\n" + "Snack " + round(
                    (alma2 / 19.92)
                ) + " calories \n\n"
        } else if (cbThreeFourDay.isChecked && cbLoseWeight.isChecked) {
            messageone =
                "Breakfast " + (verme3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme3 / 2.500) + " calories \n\n" + "Dinner " + (verme3 / 4.00) + " calories \n\n" + "Snack " + round(
                    (verme3 / 19.92)
                ) + " calories \n\n"
        } else if (cbThreeFourDay.isChecked && cbMaintainWeight.isChecked) {
            messageone =
                "Breakfast " + (koruma3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma3 / 2.500) + " calories \n\n" + "Dinner " + (koruma3 / 4.00) + " calories \n\n" + "Snack " + round(
                    (koruma3 / 19.92)
                ) + " calories \n\n"
        } else if (cbThreeFourDay.isChecked && cbGainWeight.isChecked) {
            messageone =
                "Breakfast " + (alma3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma3 / 2.500) + " calories \n\n" + "Dinner " + (alma3 / 4.00) + " calories \n\n" + "Snack " + round(
                    (alma3 / 19.92)
                ) + " calories \n\n"
        } else if (cbFiveSixDay.isChecked && cbLoseWeight.isChecked) {
            messageone =
                "Breakfast " + (verme4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme4 / 2.500) + " calories \n\n" + "Dinner " + (verme4 / 4.00) + " calories \n\n" + "Snack " + round(
                    (verme4 / 19.92)
                ) + " calories \n\n"
        } else if (cbFiveSixDay.isChecked && cbMaintainWeight.isChecked) {
            messageone =
                "Breakfast " + (koruma4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma4 / 2.500) + " calories \n\n" + "Dinner " + (koruma4 / 4.00) + " calories \n\n" + "Snack " + round(
                    (koruma4 / 19.92)
                ) + " calories \n\n"
        } else if (cbFiveSixDay.isChecked && cbGainWeight.isChecked) {
            messageone =
                "Breakfast " + (alma4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma4 / 2.500) + " calories \n\n" + "Dinner " + (alma4 / 4.00) + " calories \n\n" + "Snack " + round(
                    (alma4 / 19.92)
                ) + " calories \n\n"
        } else if (cbSevenDay.isChecked && cbLoseWeight.isChecked) {
            messageone =
                "Breakfast " + (verme5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme5 / 2.500) + " calories \n\n" + "Dinner " + (verme5 / 4.00) + " calories \n\n" + "Snack " + round(
                    (verme5 / 19.92)
                ) + " calories \n\n"
        } else if (cbSevenDay.isChecked && cbMaintainWeight.isChecked) {
            messageone =
                "Breakfast " + (koruma5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma5 / 2.500) + " calories \n\n" + "Dinner " + (koruma5 / 4.00) + " calories \n\n" + "Snack " + round(
                    (koruma5 / 19.92)
                ) + " calories \n\n"
        } else if (cbSevenDay.isChecked && cbGainWeight.isChecked) {
            messageone =
                "Breakfast " + (alma5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma5 / 2.500) + " calories \n\n" + "Dinner " + (alma5 / 4.00) + " calories \n\n" + "Snack " + round(
                    (alma5 / 19.92)
                ) + " calories \n\n"
        }

        val postMap = hashMapOf<String, Any>()
        postMap.put("userEmail", auth.currentUser!!.email.toString())
        postMap.put("bodyFat", bodyFat)
        postMap.put("dealWeight", dealWeight)
        postMap.put("message", message)
        postMap.put("messagetwo", messagetwo)
        postMap.put("messageone", messageone)
        postMap.put("date", Timestamp.now())

        db.collection("informations").add(postMap).addOnCompleteListener { task ->
            if (task.isComplete && task.isSuccessful) {
                Toast.makeText(activity, "Informations save ", Toast.LENGTH_LONG)
                    .show()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(activity, exception.localizedMessage, Toast.LENGTH_LONG)
                .show()
        }
    }
}

