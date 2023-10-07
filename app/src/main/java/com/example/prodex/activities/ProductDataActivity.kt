package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.prodex.databinding.ActivityProductDataBinding
import com.example.prodex.helpers.showSnackBar
import com.example.prodex.models.categories.Category
import com.example.prodex.utils.Status
import com.example.prodex.viewmodel.MainViewModel

class ProductDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDataBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var categoriesModel: Category
    private var listCategories = mutableListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.init(this)
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
//            if (isValid()) {
            startActivity(Intent(baseContext, ReviewActivity::class.java))
//            }
        }
//
//        viewModel.getCategories().observe(this) {
//            it?.let { resource ->
//                when (resource.status) {
//                    Status.SUCCESS -> {
//                        if (it.data?.body()?.data != null) {
//                            listCategories.addAll(it.data?.body()?.data!!)
//                            for (item in listCategories) {
//                                categoriesModel = item
//                            }
//                        }
//                    }
//
//                    Status.ERROR -> {
//                        binding.root.showSnackBar(it.message)
//                    }
//
//                    Status.LOADING -> {
//
//                    }
//
//                }
//            }
//        }

    }

    private fun isValid(): Boolean {
        if (binding.editProductName.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editReach.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editProductLink.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editAdditionalText.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else {
            return true
        }
    }
}