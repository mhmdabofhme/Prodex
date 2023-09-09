package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
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

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        binding.imgVideo.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.imgText1.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            binding.imgText1.startAnimation(anim)
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.imgText2.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            binding.imgText2.startAnimation(anim)
        }, 1500)


        Handler(Looper.getMainLooper()).postDelayed({
            binding.imgText3.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            binding.imgText3.startAnimation(anim)
        }, 2500)




        binding.btnNext.setOnClickListener {
            startActivity(Intent(baseContext,ProdexVoiceActivity::class.java))
        }
        binding.txtSkip.setOnClickListener {
            startActivity(Intent(baseContext,ProdexVoiceActivity::class.java))
        }

    }
}