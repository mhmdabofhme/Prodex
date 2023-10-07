package com.example.prodex.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.prodex.R
import com.example.prodex.databinding.ActivityProdexVoiceBinding
import com.example.prodex.helpers.Permissions
import com.example.prodex.helpers.checkStoragePermissions
import com.example.prodex.helpers.getRealPathFromURI
import com.example.prodex.helpers.requestStoragePermissions
import com.example.prodex.helpers.showSnackBar
import java.io.File


class ProdexVoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProdexVoiceBinding
    private var choice = 0
    private var audioFile: File? = null
    private var audiosList = arrayListOf<File?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdexVoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            finish()
        }


        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        binding.imgVideo.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.music1.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right_short)
            binding.music1.startAnimation(anim)
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.music2.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right_short)
            binding.music2.startAnimation(anim)
        }, 1500)


        Handler(Looper.getMainLooper()).postDelayed({
            binding.music3.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_right_short)
            binding.music3.startAnimation(anim)
        }, 2000)




//        binding.radioGroupVoice.setOnCheckedChangeListener { group, checkedId ->
//            choice = checkedId
//            Log.d("TAG", "onCreate:checkedId ${checkedId}")
//            when (checkedId) {
//                R.id.btnProdexVoice -> {
//                    binding.editText.visibility = View.VISIBLE
//                }
//
//                R.id.btnRecord -> {
//                    binding.editText.visibility = View.GONE
////                    getAudio()
//                }
//            }
//        }

        binding.btnNext.setOnClickListener {
//            when (choice) {
//                R.id.btnProdexVoice -> {
//                    startActivity(Intent(baseContext, DealerDataActivity::class.java))
//                }
//
//                R.id.btnRecord -> {
//                    startActivity(Intent(baseContext, RecordingActivity::class.java))
//                }
//            }

        }

        binding.txtSkip.setOnClickListener {
            startActivity(Intent(baseContext, DealerDataActivity::class.java))
        }

    }

    private fun getAudio() {
        if (checkStoragePermissions()) {
            val intent = Intent()
            intent.type = "audio/*"
            intent.action = Intent.ACTION_GET_CONTENT
            getAudio.launch(intent)

        } else {
            requestStoragePermissions()
        }
    }

    private val getAudio =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                     if (it.data?.data != null) {
                        var audioUri: Uri = it.data!!.data!!
                        // Convert it to file to share it to API
                        audioFile = if (audioUri.toString().contains("content", true)) {
                            getRealPathFromURI(audioUri)?.let { realPath -> File(realPath) }
                        } else {
                            File(audioUri.path ?: "")
                        }
                        audiosList.add(audioFile)

                    }
                }
                Activity.RESULT_CANCELED -> binding.root.showSnackBar(getString(R.string.snackbar))

            }
        }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Permissions.permissionStorageId -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getAudio()
                }
            }
        }
    }
}