package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.prodex.R
import com.example.prodex.databinding.ActivityProdexVoiceBinding

class ProdexVoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProdexVoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdexVoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.radioGroupVoice.setOnCheckedChangeListener { group, checkedId ->
            Log.d("TAG", "onCreate:checkedId ${checkedId}")
            when (checkedId) {
                R.id.btnProdexVoice -> {
                    binding.editText.visibility = View.VISIBLE
                }

                R.id.btnRecord -> {
                    binding.editText.visibility = View.GONE
                }
            }
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(baseContext, DealerDataActivity::class.java))
        }

        binding.txtSkip.setOnClickListener {
            startActivity(Intent(baseContext, DealerDataActivity::class.java))
        }

    }
}