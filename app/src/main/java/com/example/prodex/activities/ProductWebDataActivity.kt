package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.prodex.R
import com.example.prodex.databinding.ActivityProductWebDataBinding
import com.example.prodex.helpers.isPhone
import com.example.prodex.helpers.isValidEmail
import com.example.prodex.helpers.showSnackBar

class ProductWebDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductWebDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductWebDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }
//
        val list = arrayListOf<String>(
            getString(R.string.fashion),
            getString(R.string.cooking),
            getString(R.string.cars),
            getString(R.string.electronics),
            getString(R.string.home_and_living),
            getString(R.string.beauty_and_personal_care),
            getString(R.string.sports_and_fitness),
            getString(R.string.books_and_stationery),
            getString(R.string.health_and_wellness),
            getString(R.string.toys_and_games),
        )

        binding.spinnerCategory.setItems(list)

        binding.btnNext.setOnClickListener {
//            if (isValid()) {
                startActivity(Intent(baseContext, ProductDataActivity::class.java))
//            }
        }

//        val feelings = resources.getStringArray(R.array.product_types)
//        binding.editSpecies.setAdapter(arrayAdapter)

    }


    private fun isValid(): Boolean {
        if (binding.spinnerCategory.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editSubCategory.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editDescription.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else {
            return true
        }
    }

}