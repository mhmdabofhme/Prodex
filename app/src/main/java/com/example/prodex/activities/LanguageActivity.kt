package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.R
import com.example.prodex.databinding.ActivityLanguageBinding
import com.example.prodex.helpers.Constants
import com.example.prodex.helpers.SP
import com.example.prodex.helpers.changeLanguage
import com.example.prodex.helpers.saveLang

class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtArabic.setOnClickListener {
            saveLang("ar")
            changeLanguage(true)
            startActivity(Intent(baseContext, OnBoardingActivity::class.java))
        }
        binding.txtEnglish.setOnClickListener {
            saveLang("en")
            changeLanguage(true)
            startActivity(Intent(baseContext, OnBoardingActivity::class.java))
        }

    }
}