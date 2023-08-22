package com.example.prodex.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.R
import com.example.prodex.databinding.ActivityRecordingBinding

class RecordingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }


    }
}