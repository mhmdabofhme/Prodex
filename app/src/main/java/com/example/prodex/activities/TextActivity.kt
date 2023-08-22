package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.R
import com.example.prodex.databinding.ActivityTextBinding

class TextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(baseContext,ProdexVoiceActivity::class.java))
        }
        binding.txtSkip.setOnClickListener {
            startActivity(Intent(baseContext,ProdexVoiceActivity::class.java))
        }

    }
}