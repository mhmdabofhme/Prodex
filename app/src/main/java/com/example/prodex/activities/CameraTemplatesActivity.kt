package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.R
import com.example.prodex.adapters.GalleryTemplatesAdapter
import com.example.prodex.databinding.ActivityCameraTemplatesBinding
import com.example.prodex.databinding.ActivityGalleryTemplatesBinding

class CameraTemplatesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraTemplatesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraTemplatesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.recTemplates.adapter = GalleryTemplatesAdapter(this)
        binding.btnNext.setOnClickListener {


            startActivity(Intent(baseContext,TextActivity::class.java))


        }

    }
}