package com.example.prodex.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import com.example.prodex.R
import com.example.prodex.activities.templets.CreatVedio
import com.example.prodex.activities.templets.SharedPreferencesHelper
import com.example.prodex.databinding.ActivityReviewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import android.net.Uri
import android.net.Uri.*
import android.provider.MediaStore
import android.widget.Toast
import com.anzeh.customdialog.CustomConfirmDialog
import com.anzeh.customdialog.utils.ConfirmDialogCallBack
import com.example.prodex.activities.templets.templetOne.ShowTempletOne
import com.google.gson.Gson

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    lateinit var creatVedio: CreatVedio

    // declaring a null variable for MediaController

    // declaring a null variable for MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            finish()
        }
        val coroutineScope = CoroutineScope(Dispatchers.Default)
        val sharedPreferencesHelper = SharedPreferencesHelper(this)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        creatVedio = CreatVedio()

        binding.indicators.setImageResource(R.drawable.box)
        binding.indicators.setText("Pleas wait")
        binding.indicators.setVisibility(true)

        // declaring a null variable for VideoView


        CustomConfirmDialog
            .Builder()
            .setContext(this)                //set Title
            .setTitle("Read Carefully")
            //setSubTitle
            .setSubTitle("Pleas know that will maybe take from 1...3 minute \ndon't lock application ")
            //set Cancel Button Title
            .setButtonCancelText("CANCEL")
            //set Confirm Button Title
            .setButtonConfirmText("CONFIRM")
            //set if user can dismiss dilog true or false
            .setEnableToDismiss(true)
            //handle Clickes Buttons
            .setDialogButtonsCallBack(object : ConfirmDialogCallBack {
                override fun handleClickConfirmButton() {


                    //open sample to make vedio
                    GlobalScope.launch(Dispatchers.Default) {

                        val retrievedBitmapList = sharedPreferencesHelper.getBitmapArrayList()

                       creatVedio.convertToVideo2(
                            this@ReviewActivity,
                            retrievedBitmapList,
                            retrievedBitmapList,sharedPreferencesHelper.getName() ,sharedPreferencesHelper.getFileAudio(),20)
                       {result ,vedioPath ->

                           if(result)
                           {
                               Log.d("TAG", "handleClickConfirmButton: $vedioPath")

                               launch(Dispatchers.Main) {

                                   binding.indicators.setVisibility(false)

                               }
                               binding.videoView.setVideoPath(   vedioPath?.let { getAudioFilePath(this@ReviewActivity, it) })
                               binding.videoView.start()


                           }
                           else
                           {

                               Log.d("TAG", "handleClickConfirmButton: error")

                           }

                       }

                    }
                    coroutineScope.cancel()


                }

                override fun handleClickCancelButton() {

                }

                override fun handleWhenDialogDismiss() {

                }

            })
            //set Main Color
            .setMainColor(R.color.primary)
            //set Custom Color
            .setIcon(R.drawable.box)
            //set Corner Buttons Radius
            .setCornerButtonsRadius(40)
            //set Corner background Dialog
            .setCornerRadius(20)
            //setDialog Background
            .setDialogColorBackground(R.color.white)
            .build()
            .showConfirmDialog()







        binding.btnPost.setOnClickListener {
            startActivity(Intent(baseContext, PaymentActivity::class.java))
        }


    }
    private fun getAudioFilePath(context: Context, uri: String): String? {
        var filePath: String? = null
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = context.contentResolver.query(Uri.parse(uri), projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
                filePath = it.getString(columnIndex)
            }
        }
        return filePath
    }

}























