package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnPost.setOnClickListener {
            startActivity(Intent(baseContext, PaymentActivity::class.java))
        }


    }
}























