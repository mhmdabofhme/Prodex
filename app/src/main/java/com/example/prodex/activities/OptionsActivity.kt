package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.R
import com.example.prodex.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtGallery.setOnClickListener {
            startActivity(Intent(baseContext,GalleryTemplatesActivity::class.java))
        }
        binding.txtCamera.setOnClickListener {
            startActivity(Intent(baseContext,CameraTemplatesActivity::class.java))
        }
binding.txtSkip.setOnClickListener {
            startActivity(Intent(baseContext,DealerDataActivity::class.java))
        }

    }
}