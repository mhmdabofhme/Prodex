package com.example.prodex.activities.templets.templetOne

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.certified.customprogressindicatorlibrary.CustomProgressIndicator
import com.example.prodex.R
import com.example.prodex.activities.ReviewActivity
import com.example.prodex.activities.templets.SharedPreferencesHelper
import com.google.gson.Gson

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException



class ShowTempletOne : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var constraintLayoutContaner: ConstraintLayout
    lateinit var text1 : TextView
    lateinit var text2 : TextView
    lateinit var text3 : TextView
    lateinit var text4 : TextView
    lateinit var text5 : TextView
    lateinit var text6 : TextView
    lateinit var text7 : TextView
    lateinit var text8 : TextView
    lateinit var text9 : TextView
    lateinit var text10 : TextView
    lateinit var  indicator: CustomProgressIndicator
    //  lateinit var button: Button
    lateinit var listTextView: ArrayList<TextView>
    lateinit var listBitMap:ArrayList<Bitmap>
    var mMediaPlayer: MediaPlayer? = null
    lateinit  var _modelTempletOne: ModelTempletOne


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_templet_one)
        constraintLayoutContaner=findViewById(R.id.backGroundColor)
        image=findViewById(R.id.Image_vedio)
        text1=findViewById(R.id.text1)
        text2=findViewById(R.id.text2)
        text3=findViewById(R.id.text3)
        text4=findViewById(R.id.text4)
        text5=findViewById(R.id.text5)
        text6=findViewById(R.id.text6)
        text7=findViewById(R.id.text7)
        text8=findViewById(R.id.text8)
        text9=findViewById(R.id.text9)
        text10=findViewById(R.id.text10)
       indicator = findViewById(R.id.indicator)
        listTextView= ArrayList()
        // button=findViewById(R.id.creatVideo)
        listTextView= ArrayList()
        listBitMap= ArrayList()
        listTextView.add(text1)
        listTextView.add(text2)
        listTextView.add(text3)
        listTextView.add(text4)
        listTextView.add(text5)
        listTextView.add(text6)
        listTextView.add(text7)
        listTextView.add(text8)
        listTextView.add(text9)
        listTextView.add(text10)
        val gson = Gson()
        val lsitTemplettDataObjectAsAString = intent.getStringExtra("MylsitTempletObjectAsString")
         _modelTempletOne = gson.fromJson(
            lsitTemplettDataObjectAsAString,
            ModelTempletOne::class.java
        )


        makeChange(4,getItems(_modelTempletOne.modelText),_modelTempletOne.listColorBackGround,_modelTempletOne.ListIamgeLogo,getWindow().getDecorView(),_modelTempletOne.fileName)










    }












    @SuppressLint("ResourceAsColor")
    private fun makeChange(numberScreen:Int, listText: List<ModelText>, listColor:List<Int>, listImage: List<Int>, view: View,namefile:String)
    {
        playSound()
        lifecycleScope.launch {

            withContext(Dispatchers.IO) {
                for (i in 0 until numberScreen)
                {

                    launch (Dispatchers.Main){
                        image.setImageResource(listImage[i])
                        constraintLayoutContaner.setBackgroundColor(R.color.white)


                    }
                    for (j in 0..9)
                    {

                        listTextView.get(j).text = listText[i].text
                       // listTextView.get(j).setTextColor(Color.BLACK)

                    }
                    timer(listTextView, listColor[i], view)
                }
                launch(Dispatchers.Main) {

                    // Update or access the UI view here
                    indicator.setImageResource(R.drawable.box)
                    indicator.setVisibility(true)
                    if (mMediaPlayer != null) {
                        mMediaPlayer!!.release()
                        mMediaPlayer = null
                    }

                }

                val sharedPreferencesHelper = this@ShowTempletOne?.let { it1 -> SharedPreferencesHelper(it1) }
                if (sharedPreferencesHelper != null) {
                    sharedPreferencesHelper.deletBitMap()
                    sharedPreferencesHelper.saveBitmapArrayList(listBitMap)
                    sharedPreferencesHelper.saveName(namefile)
                    sharedPreferencesHelper.saveFileAudio(_modelTempletOne.fileaduio)
                }

                startActivity(Intent(this@ShowTempletOne, ReviewActivity::class.java))



            }
        }



    }

    @SuppressLint("ResourceAsColor")
    private suspend fun timer(textView:ArrayList< TextView>, color:Int,view: View){
        withContext(Dispatchers.Main) {
            textView.get(0).setTextColor(Color.RED)
            for(i in 1..textView.size-1)
            {
                if(i%2==0) {
                    image.animate().rotationBy(-1f).setDuration(1000).translationX(-1f).translationY(-1f)
                        .setInterpolator(AccelerateDecelerateInterpolator()).start()
                }
                else
                {
                    image.animate().rotationBy(1f).setDuration(1000).translationX(1f).translationY(1f)
                        .setInterpolator(AccelerateDecelerateInterpolator()).start()
                }
                textView.get(i).setTextColor(Color.RED)
                textView.get(i-1).setTextColor(Color.BLACK)

                delay(500)
                listBitMap.add(getBitmapFromView(view)!!)


            }


        }
    }

    private fun getItems( list:List<ModelText>): List<ModelText> {

        var temp=ArrayList<ModelText>()
        for(i in 0..list.size-1)
        {
            temp.add(list[i])
        }
        return temp

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getBitmapFromView(view: View): Bitmap? {
        //Define a bitmap with the same size as the view
        Log.d("TAG", "getBitmapFromView: "+view.width)
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable = view.background
        if (bgDrawable != null) //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas) else  //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap
        return returnedBitmap
    }

    // 1. Plays the water sound
    fun playContentUri(uri: Uri) {
        var mMediaPlayer: MediaPlayer? = null
        try {
            mMediaPlayer = MediaPlayer().apply {
                setDataSource(application, uri)
                setAudioAttributes(AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
                )
                prepare()
                start()
            }
        } catch (exception: IOException) {
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }
    fun playSound() {

        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this,Uri.parse(_modelTempletOne.fileaduio))
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    // 2. Pause playback
    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    // 3. Stops playback
    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }


}
