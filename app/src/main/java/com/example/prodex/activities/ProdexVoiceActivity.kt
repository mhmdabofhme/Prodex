package com.example.prodex.activities

import android.R.attr.duration
import android.R.id.button2
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.prodex.R
import com.example.prodex.databinding.ActivityProdexVoiceBinding
import java.io.IOException


class ProdexVoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProdexVoiceBinding
    var choice = 0
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdexVoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer()
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.radioGroupVoice.setOnCheckedChangeListener { group, checkedId ->
            choice = checkedId
            Log.d("TAG", "onCreate:checkedId ${checkedId}")
            when (checkedId) {
                R.id.btnProdexVoice -> {
                    binding.editText.visibility = View.VISIBLE
                }

                R.id.btnRecord -> {
                    binding.editText.visibility = View.GONE
//                    getAudio()
                }
            }
        }

        binding.btnNext.setOnClickListener {
            when (choice) {
                R.id.btnProdexVoice -> {
                    startActivity(Intent(baseContext, DealerDataActivity::class.java))
                }

                R.id.btnRecord -> {
                    startActivity(Intent(baseContext, RecordingActivity::class.java))
                }
            }

        }

        binding.txtSkip.setOnClickListener {
            startActivity(Intent(baseContext, DealerDataActivity::class.java))
        }


    }

//    private fun getAudio() {
//        val intent = Intent()
//        intent.type = "audio/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(intent, 1)
//
////        val intent = Intent(this, ProdexVoiceActivity::class.java)
//////        val intent = Intent()
////        intent.type = "audio/*"
////        intent.action = Intent.ACTION_GET_CONTENT
////        resultLauncher.launch(intent)
//
//    }
//
////    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
////        if (result.resultCode == Activity.RESULT_OK) {
////            // There are no request codes
////            val data: Intent? = result.data
////
//////            doSomeOperations()
////        }
////    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                //the selected audio.
//                    val uri = data?.data
//            }
//        }
//    }

}