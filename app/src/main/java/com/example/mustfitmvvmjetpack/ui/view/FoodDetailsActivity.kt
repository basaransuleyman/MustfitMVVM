package com.example.mustfitmvvmjetpack.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mustfitmvvmjetpack.databinding.ActivityFoodDetailsBinding
import com.squareup.picasso.Picasso
import java.util.*

class FoodDetailsActivity : AppCompatActivity() {

    private val _picasso = Picasso.get()
    private lateinit var _binding: ActivityFoodDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFoodDetailsBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        bindingItems()
    }

    private fun bindingItems() {
        val labelGet: String? = intent.getStringExtra("Label")
        val categoryGet: String? = intent.getStringExtra("Category")
        val calorieGet: String? = intent.getStringExtra("Calorie")
        val proteinGet: String? = intent.getStringExtra("Carbohydrate")
        val carbGet: String? = intent.getStringExtra("Protein")
        val fatGet: String? = intent.getStringExtra("Fat")

        with(_binding) {
            _picasso.load(intent.getStringExtra("SmallIcon")).into(ivSmallIcon)
            _picasso.load(intent.getStringExtra("Picture")).into(ivFood)
            tvLabel.text = labelGet?.uppercase(Locale.getDefault())
            tvCategory.text = categoryGet
            tvCalorie.text = calorieGet
            tvProtein.text = proteinGet
            tvCarb.text = carbGet
            tvFat.text = fatGet
        }

    }
}