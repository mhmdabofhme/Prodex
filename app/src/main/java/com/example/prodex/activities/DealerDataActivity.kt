package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prodex.R
import com.example.prodex.databinding.ActivityDealerDataBinding
import com.example.prodex.helpers.isPhone
import com.example.prodex.helpers.isValidEmail
import com.example.prodex.helpers.showSnackBar

class DealerDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDealerDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDealerDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
//            if (isValid()) {
                startActivity(Intent(baseContext, ProductWebDataActivity::class.java))
//            }
        }
    }

    private fun isValid(): Boolean {
        if (binding.editName.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editPhone.isPhone()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editEmail.text.trim().toString().isValidEmail()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editCountry.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else {
            return true
        }
    }

}