package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.R
import com.example.prodex.databinding.ActivityDealerDataBinding

class DealerDataActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDealerDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDealerDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(baseContext, ProductWebDataActivity::class.java))
        }


    }
}