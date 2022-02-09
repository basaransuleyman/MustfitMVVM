package com.example.mustfitmvvmjetpack.repo

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

        val wn = (request.etHip * 0) + request.etWaist - request.etNeck
        val bodyFat =
            Math.round(495 / (1.0324 - 0.19077 * log10(wn.toDouble()) + 0.15456 * log10(request.etHeight)) - 450)
        val dealWeight = Math.round((2.3 * ((request.etHeight * 0.39370079) - 60) + 50))

        bodyFatMessage(request)
        dailyNeedWeightMessage(request)
        goalWeightMessage(request)

        hashMapOf<String, Any>().apply {
            put("userEmail", _firebaseAuth.currentUser?.email.toString())
            put("name", request.etName)
            put("bodyFat", bodyFat.toString())
            put("dealWeight", dealWeight.toString())
            put("message", bodyFatMessage(request))
            put("messagetwo", dailyNeedWeightMessage(request))
            put("messageone", goalWeightMessage(request))
            put("date", Timestamp.now())
            onCompleted(this)
        }
    }

    fun getCalorie(parameter: Double, request: MathFunctionRequest): Double {
        val bmr =
            ((10 * request.etWeight) + (6.25 * request.etHeight) - (5 * request.etAge) - 161).roundToInt()
                .toDouble()

        return parameter * bmr
    }

    fun bodyFatMessage(request: MathFunctionRequest) {
        val wn = (request.etHip * 0) + request.etWaist - request.etNeck
        var message = ""
        val bodyFat =
            Math.round(495 / (1.0324 - 0.19077 * log10(wn.toDouble()) + 0.15456 * log10(request.etHeight)) - 450)
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
    }

    fun dailyNeedWeightMessage(request: MathFunctionRequest) {
        var messagetwo = " "
        messagetwo = when {
            request.cbZeroDay && request.cbLoseWeight -> {
                "To lose weight need ${getCalorie(1.2, request)} cal/day  "
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "To protect  weight need ${getCalorie(1.375, request)} cal/day "
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "To gain weight need ${getCalorie(1.55, request)} cal/day "
            }
            request.cbOneTwoDay && request.cbLoseWeight -> {
                "To lose weight need $${getCalorie(1.725, request)} cal/day "
            }
            request.cbOneTwoDay && request.cbMaintainWeight -> {
                "To protect weight need ${getCalorie(1.9, request)} cal/day "
            }
            request.cbOneTwoDay && request.cbGainWeight -> {
                "To gain weight need ${getCalorie(1.32, request)} cal/day "
            }
            request.cbThreeFourDay && request.cbLoseWeight -> {
                "To lose weight need ${getCalorie(1.512, request)} cal/day "
            }
            request.cbThreeFourDay && request.cbMaintainWeight -> {
                "To lose weight need ${getCalorie(1.704, request)} cal/day "
            }
            request.cbThreeFourDay && request.cbGainWeight -> {
                "To lose weight need ${getCalorie(1.897, request)} cal/day "
            }
            request.cbFiveSixDay && request.cbLoseWeight -> {
                "To lose weight need ${getCalorie(2.090, request)} cal/day "
            }
            request.cbFiveSixDay && request.cbMaintainWeight -> {
                "To protect weight need ${getCalorie(0.0, request)} cal/day "
            }
            request.cbFiveSixDay && request.cbGainWeight -> {
                "To gain weight need ${getCalorie(1.145, request)} cal/day "
            }
            request.cbSevenDay && request.cbLoseWeight -> {
                "To lose weight need ${getCalorie(1.291, request)} cal/day "
            }
            request.cbSevenDay && request.cbMaintainWeight -> {
                "To protect weight need ${getCalorie(1.437, request)} cal/day "
            }
            request.cbSevenDay && request.cbGainWeight -> {
                "To gain weight need ${getCalorie(1.583, request)} cal/day "
            }
            else -> "Wrong this data"
        }
    }

    fun goalWeightMessage(request: MathFunctionRequest) {
        var messageone = " "

        messageone = when {
            request.cbZeroDay && request.cbLoseWeight -> {
                "Breakfast " + ((getCalorie(
                    0.0,
                    request
                ) / 3.332)).roundToInt() + " calories \n" + "Lunch " + ((getCalorie(
                    0.0,
                    request
                ) / 2.500)) + " calories \n" + "Dinner " + ((getCalorie(
                    0.0,
                    request
                )) / 4.00) + " calories \n" + "Snack " + Math.round(
                    ((getCalorie(0.0, request)) / 19.92)
                ) + " calories \n"
            }
            request.cbZeroDay && request.cbMaintainWeight -> {
                "Breakfast " + ((getCalorie(
                    1.2,
                    request
                )) / 3.332).roundToInt() + " calories \n" + "Lunch " + ((getCalorie(
                    1.2,
                    request
                )) / 2.500) + " calories \n" + "Dinner " + ((getCalorie(
                    1.2,
                    request
                )) / 4.00) + " calories \n\n" + "Snack " + Math.round(
                    ((getCalorie(1.2, request)) / 19.92)
                ) + " calories \n\n"
            }
            /*
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
             */
            else -> "Wrong data"
        }
    }

    fun mathFunctionWoman(
        request: MathFunctionRequest,
        onCompleted: (firebaseData: HashMap<String, Any>) -> Unit
    ) {
        _application = Application()
        _firebaseAuth = Firebase.auth
        _fireStoreDB = Firebase.firestore

        val wn = request.etHip + request.etWaist - request.etNeck
        val bodyFat =
            Math.round(
                495 / (1.29579 - 0.35004 * Math.log10(wn.toDouble()) + 0.22100 * Math.log10(
                    request.etHeight
                )) - 450
            )
        val dealWeight = Math.round(2.3 * ((request.etHeight * 0.39370079) - 60) + 45.5)

        bodyFatMessage(request)
        dailyNeedWeightMessage(request)
        goalWeightMessage(request)

        hashMapOf<String, Any>().apply {
            put("userEmail", _firebaseAuth.currentUser!!.email.toString())
            put("name", request.etName)
            put("bodyFat", bodyFat.toString())
            put("dealWeight", dealWeight.toString())
            put("message", bodyFatMessage(request))
            put("messagetwo", dailyNeedWeightMessage(request))
            put("messageone", goalWeightMessage(request))
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
