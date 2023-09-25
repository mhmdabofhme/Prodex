package com.example.prodex.activities.templets.templetOne

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
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
import android.view.animation.TranslateAnimation
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
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.math.log


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
    private lateinit var view2: ConstraintLayout
    private var opened: Boolean = false


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

        view2 = findViewById(R.id.finalview)
        //view2.visibility = View.INVISIBLE
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
                       // constraintLayoutContaner.setBackgroundColor(R.color.white)
                    }
                    for (j in 0..9)
                    {
                        listTextView.get(j).text = listText[i].text

                    }
                    timer(listTextView, listColor[i], view)
                }


                launch(Dispatchers.Main) {
                    val animationDuration = 5000L // Duration of the animation in milliseconds
                    val actionInterval = 100L // Interval for the additional action in milliseconds

                    performAnimationWithAction(animationDuration, {
                        Log.d("TAG", "makeChange:....... ")
                        getBitmapFromView(view)?.let { listBitMap.add(it) }

                    }, actionInterval,namefile)

                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private suspend fun timer(textView:ArrayList< TextView>, color:Int,view: View){
        withContext(Dispatchers.Main) {
            textView.get(0).setTextColor(Color.YELLOW)
            for(i in 1..textView.size-1)
            {
//                if(i%2==0) {
//                    image.animate().rotationBy(-1f).setDuration(1000).translationX(-1f).translationY(-1f)
//                        .setInterpolator(AccelerateDecelerateInterpolator()).start()
//                }
//                else
//                {
//                    image.animate().rotationBy(1f).setDuration(1000).translationX(1f).translationY(1f)
//                        .setInterpolator(AccelerateDecelerateInterpolator()).start()
//                }

                textView.get(i).setTextColor(Color.YELLOW)
                textView.get(i-1).setTextColor(Color.GRAY)

                delay(500)
                listBitMap.add(getBitmapFromView(view)!!)


            }


        }
    }
   fun performAnimationWithAction(animationDurationInMillis: Long, action: () -> Unit, actionIntervalInMillis: Long,namefile:String) {
        val viewToAnimate = findViewById<View>(R.id.finalview)
        viewToAnimate.visibility = View.VISIBLE
        viewToAnimate.translationY = -viewToAnimate.height.toFloat()
        val animator = ValueAnimator.ofFloat(-viewToAnimate.height.toFloat(), 0f)
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = animationDurationInMillis

        animator.addUpdateListener { valueAnimator ->
            val translationY = valueAnimator.animatedValue as Float
            viewToAnimate.translationY = translationY
        }

        val handler = Handler(Looper.getMainLooper())
        val actionRunnable = object : Runnable {
            override fun run() {
                action()
                handler.postDelayed(this, actionIntervalInMillis)
            }
        }

        handler.postDelayed(actionRunnable, actionIntervalInMillis)
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {




                        indicator.setImageResource(R.drawable.box)
                        indicator.setVisibility(true)
                        stopSound()
                       // mainThreadDeferred.complete(Unit)
                  // mainThreadDeferred.await()
                runBlocking(Dispatchers.IO) {
                    Log.d("TAG", "makeChange:size ${listBitMap.size} ")
                    val sharedPreferencesHelper = this@ShowTempletOne?.let { it1 -> SharedPreferencesHelper(it1) }
                    if (sharedPreferencesHelper != null) {
                        sharedPreferencesHelper.deletBitMap()
                        sharedPreferencesHelper.saveBitmapArrayList(listBitMap)
                        sharedPreferencesHelper.saveName(namefile)
                        sharedPreferencesHelper.saveFileAudio(_modelTempletOne.fileaduio)
                    }
                    startActivity(Intent(this@ShowTempletOne, ReviewActivity::class.java))

                }


                handler.removeCallbacks(actionRunnable)
            }

            override fun onAnimationCancel(p0: Animator) {
                handler.removeCallbacks(actionRunnable)
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })

        animator.start()

        handler.postDelayed({
            animator.cancel()
            handler.removeCallbacks(actionRunnable)
        }, animationDurationInMillis)
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
        Log.d("TAG", "getBitmapFromView: ${listBitMap.size} ")
        return returnedBitmap
    }

    // 1. Plays the water sound
    fun playSound() {

        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this,Uri.parse(_modelTempletOne.fileaduio))
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
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
