package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.R
import com.example.prodex.databinding.ActivityProductDataBinding

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
            startActivity(Intent(baseContext,ReviewActivity::class.java))
        }

    }
}