package com.example.prodex.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.prodex.R
import com.example.prodex.databinding.ActivityLanguageBinding
import com.example.prodex.helpers.Constants
import com.example.prodex.helpers.Permissions
import com.example.prodex.helpers.SP
import com.example.prodex.helpers.changeLanguage
import com.example.prodex.helpers.checkNotificationPermissions
import com.example.prodex.helpers.requestNotificationPermissions
import com.example.prodex.helpers.saveLang
import com.example.prodex.helpers.showSnackBar

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

        binding.checkboxSaveData.isChecked = checkNotificationPermissions()

        binding.checkboxSaveData.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                startActivity(intent)
            }
        }

    }

}