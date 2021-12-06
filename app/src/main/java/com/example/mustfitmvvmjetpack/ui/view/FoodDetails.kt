package com.example.mustfitmvvmjetpack.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.mustfitmvvmjetpack.R
import com.squareup.picasso.Picasso
import java.util.*

class FoodDetails : AppCompatActivity() {
    private lateinit var _getLabel: TextView
    private lateinit var _getCategory: TextView
    private lateinit var _getSmallIcon: ImageView
    private lateinit var _getFoodImage: ImageView
    private lateinit var _getCalorie: TextView
    private lateinit var _getProtein: TextView
    private lateinit var _getCarb: TextView
    private lateinit var _getFat: TextView
    private val _picasso = Picasso.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        _getLabel = findViewById(R.id.tvLabel)
        _getCategory = findViewById(R.id.tvCategory)
        _getSmallIcon = findViewById(R.id.ivSmallIcon)
        _getFoodImage = findViewById(R.id.ivFood)
        _getCalorie = findViewById(R.id.tvCalorie)
        _getProtein = findViewById(R.id.tvProtein)
        _getCarb = findViewById(R.id.tvCarb)
        _getFat = findViewById(R.id.tvFat)

        val labelGet: String? = intent.getStringExtra("Label")
        val categoryGet: String? = intent.getStringExtra("Category")
        val calorieGet: String? = intent.getStringExtra("Calorie")
        val proteinGet: String? = intent.getStringExtra("Carbohydrate")
        val carbGet: String? = intent.getStringExtra("Protein")
        val fatGet: String? = intent.getStringExtra("Fat")

        _picasso.load(getIntent().getStringExtra("SmallIcon")).into(_getSmallIcon)
        _picasso.load(getIntent().getStringExtra("Picture")).into(_getFoodImage)
        _getLabel.text = labelGet?.uppercase(Locale.getDefault())
        _getCategory.text = categoryGet
        _getCalorie.text = calorieGet
        _getProtein.text = proteinGet
        _getCarb.text = carbGet
        _getFat.text = fatGet

    }
}