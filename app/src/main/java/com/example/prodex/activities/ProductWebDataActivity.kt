package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.prodex.R
import com.example.prodex.databinding.ActivityProductWebDataBinding
import com.example.prodex.helpers.isPhone
import com.example.prodex.helpers.isValidEmail
import com.example.prodex.helpers.showSnackBar
import com.example.prodex.models.categories.Category
import com.example.prodex.utils.Status
import com.example.prodex.viewmodel.MainViewModel

class ProductWebDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductWebDataBinding

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var categoriesModel: Category
    private var listCategories = mutableListOf<Category>()
    private var categoryId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductWebDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.init(this)

        binding.imgBack.setOnClickListener {
            finish()
        }


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

//        binding.spinnerCategory.setItems(list)

        binding.btnNext.setOnClickListener {
            if (isValid()) {
                startActivity(Intent(baseContext, ProductDataActivity::class.java))
            }
        }

//        val feelings = resources.getStringArray(R.array.product_types)
//        binding.editSpecies.setAdapter(arrayAdapter)


        viewModel.getCategories().observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (it.data?.body()?.data != null) {
                            listCategories.addAll(it.data.body()?.data!!)
                            val list = mutableListOf<String>()
                            for (category in listCategories) {
                                list.add(category.name)
                            }
                            binding.spinnerCategory.setItems(list)

                        }
                    }

                    Status.ERROR -> {
                        Log.w("TAG", "onCreate: ${it.message}")
                        binding.root.showSnackBar(it.message)
                    }

                    Status.LOADING -> {

                    }

                }
            }
        }

    }


    private fun isValid(): Boolean {
        for (item in listCategories) {
            if (binding.spinnerCategory.text == item.name) {
                Log.d("TAG", "category: $item")
                categoriesModel = item
                categoryId = item.id
            }
        }

        return if (binding.spinnerCategory.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            false
        } else if (binding.editSubCategory.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            false
        } else if (binding.editDescription.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            false
        } else if (categoryId != 0) {
            binding.root.showSnackBar()
            false
        } else {
            true
        }
    }

}