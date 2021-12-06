package com.example.mustfitmvvmjetpack.data.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.mustfitmvvmjetpack.ui.adapter.MustfitRecyclerAdapter
import com.example.mustfitmvvmjetpack.model.MustfitModel
import com.example.mustfitmvvmjetpack.utils.MathFunctionRequest
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log10
import kotlin.math.roundToInt

class DataRepository() {

    private lateinit var _application: Application
    private lateinit var _firebaseAuth: FirebaseAuth
    private lateinit var _fireStoreDB: FirebaseFirestore
    private lateinit var _messageLiveData: MutableLiveData<String>
    private val _informationArrayList: ArrayList<MustfitModel> = ArrayList()
    private var _adapter: MustfitRecyclerAdapter? = null

    fun mathFunctionMan(
        request: MathFunctionRequest,
        onCompleted: (firebaseData: HashMap<String, Any>) -> Unit
    ) {
        _application = Application()
        _firebaseAuth = Firebase.auth
        _fireStoreDB = Firebase.firestore

        var message = " "
        var messagetwo = " "
        var messageone = " "
        val wn = (request.etHip * 0) + request.etWaist - request.etNeck
        val bodyFat =
            Math.round(495 / (1.0324 - 0.19077 * log10(wn.toDouble()) + 0.15456 * log10(request.etHeight)) - 450)
        val dealWeight = Math.round((2.3 * ((request.etHeight * 0.39370079) - 60) + 50))
        val bmr =
            Math.round(5 + (10 * request.etWeight) + (6.25 * request.etHeight) - (5 * request.etAge))
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

        message = when {
            bodyFat.toDouble() in 2.0..5.0 -> {
                "Body fat is Essential for life "
            }
            bodyFat.toDouble() in 6.0..13.0 -> {
                "Body fat is Athletes "
            }
            bodyFat.toDouble() in 14.0..17.0 -> {
                "Body fat is People in shape "
            }
            bodyFat.toDouble() in 18.0..24.0 -> {
                "Boody fat is Normal "
            }
            bodyFat.toDouble() >= 25.0 -> {
                "Body fat is Obese "
            }
            else -> {
                "Wrong body fat "
            }
        }

        messagetwo = when {
            request.cbZeroDay && request.cbLoseWeight -> {
                "To lose weight need $verme1 cal/day  "
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "To protect  weight need $koruma1 cal/day "
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "To gain weight need $alma1 cal/day "
            }
            request.cbOneTwoDay && request.cbLoseWeight -> {
                "To lose weight need $verme2 cal/day "
            }
            request.cbOneTwoDay && request.cbMaintainWeight -> {
                "To protect weight need $koruma2 cal/day "
            }
            request.cbOneTwoDay && request.cbGainWeight -> {
                "To gain weight need $alma2 cal/day "
            }
            request.cbThreeFourDay && request.cbLoseWeight -> {
                "To lose weight need $verme3 cal/day "
            }
            request.cbThreeFourDay && request.cbMaintainWeight -> {
                "To lose weight need $koruma3 cal/day "
            }
            request.cbThreeFourDay && request.cbGainWeight -> {
                "To lose weight need $alma3 cal/day "
            }
            request.cbFiveSixDay && request.cbLoseWeight -> {
                "To lose weight need $verme4 cal/day "
            }
            request.cbFiveSixDay && request.cbMaintainWeight -> {
                "To protect weight need $koruma4 cal/day "
            }
            request.cbFiveSixDay && request.cbGainWeight -> {
                "To gain weight need $alma4 cal/day "
            }
            request.cbSevenDay && request.cbLoseWeight -> {
                "To lose weight need $verme5 cal/day "
            }
            request.cbSevenDay && request.cbMaintainWeight -> {
                "To protect weight need $koruma5 cal/day "
            }
            request.cbSevenDay && request.cbGainWeight -> {
                "To gain weight need $alma5 cal/day "
            }
            else -> "Wrong this data"
        }

        messageone = when {
            request.cbZeroDay && request.cbLoseWeight -> {
                "Breakfast " + (verme1 / 3.332).roundToInt() + " calories \n" + "Lunch " + (verme1 / 2.500) + " calories \n" + "Dinner " + (verme1 / 4.00) + " calories \n" + "Snack " + Math.round(
                    (verme1 / 19.92)
                ) + " calories \n"
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma1 / 3.332).roundToInt() + " calories \n" + "Lunch " + (koruma1 / 2.500) + " calories \n" + "Dinner " + (koruma1 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma1 / 19.92)
                ) + " calories \n\n"
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "Breakfast " + (alma1 / 3.332).roundToInt() + " calories \n" + "Lunch " + (alma1 / 2.500) + " calories \n\n" + "Dinner " + (alma1 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma1 / 19.92)
                ) + " calories \n\n"
            }
            request.cbOneTwoDay && request.cbLoseWeight -> {
                "Breakfast " + (verme2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme2 / 2.500) + " calories \n\n" + "Dinner " + (verme2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme2 / 19.92)
                ) + " calories \n\n"
            }
            request.cbOneTwoDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma2 / 2.500) + " calories \n\n" + "Dinner " + (koruma2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma2 / 19.92)
                ) + " calories \n\n"
            }
            request.cbOneTwoDay && request.cbGainWeight -> {
                "Breakfast " + (alma2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma2 / 2.500) + " calories \n\n" + "Dinner " + (alma2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma2 / 19.92)
                ) + " calories \n\n"
            }
            request.cbThreeFourDay && request.cbLoseWeight -> {
                "Breakfast " + (verme3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme3 / 2.500) + " calories \n\n" + "Dinner " + (verme3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme3 / 19.92)
                ) + " calories \n\n"
            }
            request.cbThreeFourDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma3 / 2.500) + " calories \n\n" + "Dinner " + (koruma3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma3 / 19.92)
                ) + " calories \n\n"
            }
            request.cbThreeFourDay && request.cbGainWeight -> {
                "Breakfast " + (alma3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma3 / 2.500) + " calories \n\n" + "Dinner " + (alma3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma3 / 19.92)
                ) + " calories \n\n"
            }
            request.cbFiveSixDay && request.cbLoseWeight -> {
                "Breakfast " + (verme4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme4 / 2.500) + " calories \n\n" + "Dinner " + (verme4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme4 / 19.92)
                ) + " calories \n\n"
            }
            request.cbFiveSixDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma4 / 2.500) + " calories \n\n" + "Dinner " + (koruma4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma4 / 19.92)
                ) + " calories \n\n"
            }
            request.cbFiveSixDay && request.cbGainWeight -> {
                "Breakfast " + (alma4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma4 / 2.500) + " calories \n\n" + "Dinner " + (alma4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma4 / 19.92)
                ) + " calories \n\n"
            }
            request.cbSevenDay && request.cbLoseWeight -> {
                "Breakfast " + (verme5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme5 / 2.500) + " calories \n\n" + "Dinner " + (verme5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme5 / 19.92)
                ) + " calories \n\n"
            }
            request.cbSevenDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma5 / 2.500) + " calories \n\n" + "Dinner " + (koruma5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma5 / 19.92)
                ) + " calories \n\n"
            }
            request.cbSevenDay && request.cbGainWeight -> {
                "Breakfast " + (alma5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma5 / 2.500) + " calories \n\n" + "Dinner " + (alma5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma5 / 19.92)
                ) + " calories \n\n"
            }
            else -> "Wrong data"
        }

        hashMapOf<String, Any>().apply {
            put("userEmail", _firebaseAuth.currentUser?.email.toString())
            put("name", request.etName)
            put("bodyFat", bodyFat.toString())
            put("dealWeight", dealWeight.toString())
            put("message", message)
            put("messagetwo", messagetwo)
            put("messageone", messageone)
            put("date", Timestamp.now())
            onCompleted(this)
        }
    }


    fun mathFunctionWoman(
        request: MathFunctionRequest,
        onCompleted: (firebaseData: HashMap<String, Any>) -> Unit
    ) {
        _application = Application()
        _firebaseAuth = Firebase.auth
        _fireStoreDB = Firebase.firestore

        var message = " "
        var messagetwo = " "
        var messageone = " "
        val wn = request.etHip + request.etWaist - request.etNeck
        val bodyFat =
            Math.round(
                495 / (1.29579 - 0.35004 * Math.log10(wn.toDouble()) + 0.22100 * Math.log10(
                    request.etHeight
                )) - 450
            )
        val dealWeight = Math.round(2.3 * ((request.etHeight * 0.39370079) - 60) + 45.5)
        val bmr =
            Math.round(((10 * request.etWeight) + (6.25 * request.etHeight) - (5 * request.etAge) - 161))
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

        message = when {
            bodyFat in 10..13 -> {
                "Body fat is Essential for life"
            }
            bodyFat in 14..20 -> {
                "Body fat is Athletes"
            }
            bodyFat in 21..24 -> {
                "Body fat is People in shape"
            }
            bodyFat in 25..31 -> {
                "Body fat is Normal"
            }
            bodyFat >= 32 -> {
                "Body fat is Obese"
            }
            else -> {
                "Body fat is Wrong body fat"
            }
        }

        messagetwo = when {
            request.cbZeroDay && request.cbLoseWeight -> {
                "To lose weight need $verme1 cal/day  "
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "To protect  weight need $koruma1 cal/day "
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "To gain weight need $alma1 cal/day "
            }
            request.cbOneTwoDay && request.cbLoseWeight -> {
                "To lose weight need $verme2 cal/day "
            }
            request.cbOneTwoDay && request.cbMaintainWeight -> {
                "To protect weight need $koruma2 cal/day "
            }
            request.cbOneTwoDay && request.cbGainWeight -> {
                "To gain weight need $alma2 cal/day "
            }
            request.cbThreeFourDay && request.cbLoseWeight -> {
                "To lose weight need $verme3 cal/day "
            }
            request.cbThreeFourDay && request.cbMaintainWeight -> {
                "To lose weight need $koruma3 cal/day "
            }
            request.cbThreeFourDay && request.cbGainWeight -> {
                "To lose weight need $alma3 cal/day "
            }
            request.cbFiveSixDay && request.cbLoseWeight -> {
                "To lose weight need $verme4 cal/day "
            }
            request.cbFiveSixDay && request.cbMaintainWeight -> {
                "To protect weight need $koruma4 cal/day "
            }
            request.cbFiveSixDay && request.cbGainWeight -> {
                "To gain weight need $alma4 cal/day "
            }
            request.cbSevenDay && request.cbLoseWeight -> {
                "To lose weight need $verme5 cal/day "
            }
            request.cbSevenDay && request.cbMaintainWeight -> {
                "To protect weight need $koruma5 cal/day "
            }
            request.cbSevenDay && request.cbGainWeight -> {
                "To gain weight need $alma5 cal/day "
            }
            else -> "Wrong data"
        }

        messageone = when {
            request.cbZeroDay && request.cbLoseWeight -> {
                "Breakfast " + (verme1 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme1 / 2.500) + " calories \n\n" + "Dinner " + (verme1 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme1 / 19.92)
                ) + " calories \n\n"
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma1 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma1 / 2.500) + " calories \n\n" + "Dinner " + (koruma1 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma1 / 19.92)
                ) + " calories \n\n"
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "Breakfast " + (alma1 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma1 / 2.500) + " calories \n\n" + "Dinner " + (alma1 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma1 / 19.92)
                ) + " calories \n\n"
            }
            request.cbOneTwoDay && request.cbLoseWeight -> {
                "Breakfast " + (verme2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme2 / 2.500) + " calories \n\n" + "Dinner " + (verme2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme2 / 19.92)
                ) + " calories \n\n"
            }
            request.cbOneTwoDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma2 / 2.500) + " calories \n\n" + "Dinner " + (koruma2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma2 / 19.92)
                ) + " calories \n\n"
            }
            request.cbOneTwoDay && request.cbGainWeight -> {
                "Breakfast " + (alma2 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma2 / 2.500) + " calories \n\n" + "Dinner " + (alma2 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma2 / 19.92)
                ) + " calories \n\n"
            }
            request.cbThreeFourDay && request.cbLoseWeight -> {
                "Breakfast " + (verme3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme3 / 2.500) + " calories \n\n" + "Dinner " + (verme3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme3 / 19.92)
                ) + " calories \n\n"
            }
            request.cbThreeFourDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma3 / 2.500) + " calories \n\n" + "Dinner " + (koruma3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma3 / 19.92)
                ) + " calories \n\n"
            }
            request.cbThreeFourDay && request.cbGainWeight -> {
                "Breakfast " + (alma3 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma3 / 2.500) + " calories \n\n" + "Dinner " + (alma3 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma3 / 19.92)
                ) + " calories \n\n"
            }
            request.cbFiveSixDay && request.cbLoseWeight -> {
                "Breakfast " + (verme4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme4 / 2.500) + " calories \n\n" + "Dinner " + (verme4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme4 / 19.92)
                ) + " calories \n\n"
            }
            request.cbFiveSixDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma4 / 2.500) + " calories \n\n" + "Dinner " + (koruma4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma4 / 19.92)
                ) + " calories \n\n"
            }
            request.cbFiveSixDay && request.cbGainWeight -> {
                "Breakfast " + (alma4 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma4 / 2.500) + " calories \n\n" + "Dinner " + (alma4 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma4 / 19.92)
                ) + " calories \n\n"
            }
            request.cbSevenDay && request.cbLoseWeight -> {
                "Breakfast " + (verme5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (verme5 / 2.500) + " calories \n\n" + "Dinner " + (verme5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (verme5 / 19.92)
                ) + " calories \n\n"
            }
            request.cbSevenDay && request.cbMaintainWeight -> {
                "Breakfast " + (koruma5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (koruma5 / 2.500) + " calories \n\n" + "Dinner " + (koruma5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (koruma5 / 19.92)
                ) + " calories \n\n"
            }
            request.cbSevenDay && request.cbGainWeight -> {
                "Breakfast " + (alma5 / 3.332).roundToInt() + " calories \n\n" + "Lunch " + (alma5 / 2.500) + " calories \n\n" + "Dinner " + (alma5 / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    (alma5 / 19.92)
                ) + " calories \n\n"
            }
            else -> "Wrong data"
        }

        hashMapOf<String, Any>().apply {
            put("userEmail", _firebaseAuth.currentUser!!.email.toString())
            put("name", request.etName)
            put("bodyFat", bodyFat.toString())
            put("dealWeight", dealWeight.toString())
            put("message", message)
            put("messagetwo", messagetwo)
            put("messageone", messageone)
            put("date", Timestamp.now())
            onCompleted(this)
        }
    }

    @SuppressWarnings
    fun getDataFromDB() {
        _firebaseAuth = Firebase.auth
        _fireStoreDB = Firebase.firestore
        _fireStoreDB.collection("informations").orderBy(
            "userEmail", com.google.firebase.firestore.Query.Direction.DESCENDING
        ).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                _messageLiveData.postValue("exception localizedMessage")
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
