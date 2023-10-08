package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.prodex.R
import com.example.prodex.databinding.ActivityDealerDataBinding
import com.example.prodex.helpers.SP
import com.example.prodex.helpers.isPhone
import com.example.prodex.helpers.isValidEmail
import com.example.prodex.helpers.showSnackBar
import com.example.prodex.models.user.User

class DealerDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDealerDataBinding
    private var isChecked = false
    private var name = ""
    private var phone = ""
    private var email = ""
    private var country = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDealerDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            if (isValid()) {
                if (isChecked) {
                    SP.getInstance(this).saveUser(User(name, email, phone, country))
                }
                startActivity(Intent(baseContext, ProductWebDataActivity::class.java))
            }
        }
        binding.checkboxSaveData.setOnCheckedChangeListener { compoundButton, b ->
            isChecked = b
        }


    }

    private fun isValid(): Boolean {
        if (binding.editName.text.trim().isEmpty()) {
            name = binding.editName.text.trim().toString()
            binding.root.showSnackBar()
            return false
        } else if (binding.editPhone.isPhone()) {
            phone = binding.editPhone.text.trim().toString()
            binding.root.showSnackBar()
            return false
        } else if (binding.editEmail.text.trim().toString().isValidEmail()) {
            email = binding.editEmail.text.trim().toString()
            binding.root.showSnackBar()
            return false
        } else if (binding.editCountry.text.trim().isEmpty()) {
            country = binding.editCountry.text.trim().toString()
            binding.root.showSnackBar()
            return false
        } else {
            return true
        }
    }

}