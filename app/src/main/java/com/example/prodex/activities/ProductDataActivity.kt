package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.databinding.ActivityProductDataBinding
import com.example.prodex.helpers.showSnackBar

class ProductDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
//            if (isValid()) {
            startActivity(Intent(baseContext, ReviewActivity::class.java))
//            }
        }

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