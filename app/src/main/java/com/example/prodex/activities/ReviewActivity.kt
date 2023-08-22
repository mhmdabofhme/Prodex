package com.example.prodex.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.TextView
import com.example.prodex.databinding.ActivityReviewBinding
import com.example.prodex.helpers.initProgress
import com.example.prodex.helpers.showSnackBar
import com.kaopiz.kprogresshud.KProgressHUD

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    private lateinit var progress: KProgressHUD


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progress = initProgress()
        binding.root.showSnackBar()
        binding.imgBack.setOnClickListener {
            finish()
        }
//        progress.show()
//        val mediaController = MediaController(this)
//        mediaController.setAnchorView(binding.videoView)
//        val uri = Uri.parse("https://www.youtube.com/watch?v=UQ4Yg_HUBFY")
//        val uri = Uri.parse("http://web.logiclabsolution.com/xxx.mp4")
//        binding.videoView.setVideoURI(uri)
//        binding.videoView.start()
//        binding.videoView.setMediaController(mediaController)

        binding.btnPost.setOnClickListener {
            startActivity(Intent(baseContext,PaymentActivity::class.java))
        }

    }
}























