package com.example.prodex.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import com.example.prodex.R
import com.example.prodex.databinding.ActivitySplashScreenBinding
import com.example.prodex.helpers.Permissions
import com.example.prodex.helpers.checkNotificationPermissions
import com.example.prodex.helpers.getRealPathFromURI
import com.example.prodex.helpers.requestNotificationPermissions
import com.example.prodex.helpers.showSnackBar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isFlexibleUpdateAllowed
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import java.io.File

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var appUpdateManager: AppUpdateManager
    private val updateType = AppUpdateType.IMMEDIATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
//        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
//        checkForAppUpdates()
        setContentView(binding.root)

        val animation = AnimationUtils.loadAnimation(this, R.anim.scale)
        binding.imgLogo.startAnimation(animation)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LanguageActivity::class.java))
            finishAffinity()
        }, 2000)

    }


    private fun checkForAppUpdates() {
        Log.d("TAG", "checkForAppUpdates: ${updateType}")
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            Log.d("TAG", "checkForAppUpdates:info ${info}")

            val isUpdateAvailable = info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            val isUpdateAllowed = when (updateType) {
                AppUpdateType.FLEXIBLE -> info.isFlexibleUpdateAllowed
                AppUpdateType.IMMEDIATE -> info.isImmediateUpdateAllowed
                else -> false
            }
            if (isUpdateAvailable && isUpdateAllowed) {
                appUpdateManager.startUpdateFlowForResult(
                    info,
                    updateType,
                    this,
                    123
                )
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode != RESULT_OK) {
                binding.root.showSnackBar(getString(R.string.something_went_wrong_updating))
            } else if (resultCode == RESULT_OK) {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, LanguageActivity::class.java))
                    finishAffinity()
                }, 2000)
            }
        }
    }
}