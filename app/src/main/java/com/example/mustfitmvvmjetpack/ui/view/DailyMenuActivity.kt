package com.example.mustfitmvvmjetpack.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mustfitmvvmjetpack.databinding.ActivityDailyMenuBinding
import com.squareup.picasso.Picasso
import java.util.*

class DailyMenuActivity : AppCompatActivity() {

    private val _picasso = Picasso.get()
    private lateinit var _binding: ActivityDailyMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDailyMenuBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        bindingItems()
    }

    private fun bindingItems() {
        val labelGet: String? = intent.getStringExtra(NAME)
        val calorieGet: String? = intent.getStringExtra(CALORIE)
        val countGet: String? = intent.getStringExtra(COUNT)

        with(_binding) {
            _picasso.load(intent.getStringExtra(PICTURE)).into(_binding.ivFoodDaily)
            tvFoodLabelDaily.text = labelGet?.uppercase(Locale.getDefault())
            tvCaloriesDaily.text = calorieGet
            tvNumber.text = countGet
        }

    }

    companion object {
        private const val PICTURE = "Picture"
        private const val CALORIE = "TotalCalories"
        private const val NAME = "FoodName"
        private const val COUNT = "Count"
    }

}