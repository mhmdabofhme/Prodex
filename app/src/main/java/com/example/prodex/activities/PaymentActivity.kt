package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.prodex.R
import com.example.prodex.databinding.ActivityPaymentBinding
import com.example.prodex.helpers.getCountries
import com.example.prodex.helpers.getTargetAge
import com.example.prodex.viewmodel.MainViewModel

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private val viewModel by viewModels<MainViewModel>()

    private var amount = 29
    private var total = 29

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }
        viewModel.counter.observe(this) {
            it.let {
//                count = it
                total = it + amount
                binding.txtCount.text = it.toString()
                binding.txtTotalPayment.text = "$$total"
            }
        }


        binding.spinnerTargetAge.setItems(getTargetAge())
        binding.spinnerTargetPlace.setItems(getCountries())

        binding.imgPlus.setOnClickListener {
            viewModel.plus()
        }
        binding.imgMinus.setOnClickListener {
            if (viewModel.counter.value?.toInt()?.equals(0) != true) {
                viewModel.minus()
            }
        }
        binding.layoutPayment.setOnClickListener {
            startActivity(Intent(this, FinishActivity::class.java))
        }

    }
}