package com.example.mustfitmvvmjetpack.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.mustfitmvvmjetpack.R
import com.squareup.picasso.Picasso
import java.util.*

class DailyMenu : AppCompatActivity() {

    private lateinit var _getLabel: TextView
    private lateinit var _getCalorie: TextView
    private lateinit var _getImage: ImageView
    private lateinit var _getCount: TextView
    private val _picasso = Picasso.get()

    companion object {
        const val PICTURE = "Picture"
        const val CALORIE = "TotalCalories"
        const val NAME = "FoodName"
        const val COUNT = "Count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_menu)

        _getLabel = findViewById(R.id.tvFoodLabelDaily)
        _getCalorie = findViewById(R.id.tvCaloriesDaily)
        _getCount = findViewById(R.id.tvNumber)
        _getImage = findViewById(R.id.ivFoodDaily)


        val labelGet: String? = intent.getStringExtra(NAME)
        val calorieGet: String? = intent.getStringExtra(CALORIE)
        val countGet: String? = intent.getStringExtra(COUNT)
        _picasso.load(intent.getStringExtra(PICTURE)).into(_getImage)

        _getLabel.text = labelGet?.uppercase(Locale.getDefault())
        _getCalorie.text = calorieGet
        _getCount.text = countGet

    }
}