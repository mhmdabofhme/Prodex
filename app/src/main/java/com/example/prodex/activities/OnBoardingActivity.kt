package com.example.prodex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.prodex.R
import com.example.prodex.databinding.ActivityOnBoardingBinding
import com.example.prodex.databinding.ItemOnBoardingBinding
import com.example.prodex.view_pager.ViewPagerAdapter

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var boardList: ArrayList<String>
    private lateinit var titleList: ArrayList<String>
    private lateinit var adapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)

//                handler.postDelayed(runnable, 4000)
                if (position == 2) {
                    binding.btnNext.text = getString(R.string.start)
                    binding.btnNext.setOnClickListener {
                        startActivity(Intent(baseContext, OptionsActivity::class.java))
                    }
                    Log.d("TAG", "onPageSelected: ")
                    adapter.showSocialMedia(true,this@OnBoardingActivity)
//                    Handler().postDelayed({
//                        startActivity(Intent(baseContext, SplashScreen::class.java))
//                    }, 2000)
                } else {
//                    adapter.showSocialMedia(onBoard,false,this@OnBoardingActivity)
                    binding.btnNext.text = getString(R.string.next)
                }
            }
        })

        binding.btnNext.setOnClickListener {
            viewPager2.currentItem = viewPager2.currentItem + 1
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }


    private fun init() {

        viewPager2 = binding.viewPager
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()
        titleList = ArrayList()
        boardList = ArrayList()

        imageList.add(R.drawable.box)
        imageList.add(R.drawable.clip)
        imageList.add(R.drawable.share)
        boardList.add(getString(R.string.board1))
        boardList.add(getString(R.string.board2))
        boardList.add(getString(R.string.board3))
        titleList.add(getString(R.string.title1))
        titleList.add(getString(R.string.title2))
        titleList.add(getString(R.string.title3))

        adapter = ViewPagerAdapter(this,imageList, titleList, viewPager2)
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.dotsIndicator.attachTo(viewPager2);
    }
}