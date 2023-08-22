package com.example.prodex.view_pager

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.prodex.R
import com.example.prodex.databinding.ItemOnBoardingBinding

class ViewPagerAdapter(
    private val activity: Activity,
    private val imageList: ArrayList<Int>,
    private val titleList: ArrayList<String>,
    private val viewPager2: ViewPager2
) :
    RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

    lateinit var imgInstagram: ImageView
    lateinit var imgTwitter: ImageView
    lateinit var imgTiktok: ImageView
    lateinit var imgSnapchat: ImageView


    class ImageViewHolder(view: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(view.root) {
        val binding: ItemOnBoardingBinding = view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view: ItemOnBoardingBinding =
            ItemOnBoardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.binding.imgOnBoarding.setImageResource(imageList[position])
        holder.binding.txtTitle.text = titleList[position]

        imgInstagram = holder.binding.imgInstagram
        imgTwitter = holder.binding.imgTwitter
        imgTiktok = holder.binding.imgTiktok
        imgSnapchat = holder.binding.imgSnapchat
//        if (position!=imageList.size-1){
//            showSocialMedia(holder.binding,false, activity)
//        }else{
//            showSocialMedia(holder.binding,true,activity)
//
//        }
//        if (position == imageList.size - 1) {
//            viewPager2.post(runnable)
//        }
    }


    override fun getItemCount(): Int {
        return imageList.size
    }

    fun showSocialMedia(boolean: Boolean, activity: Activity) {
        Log.d("TAG", "showSocialMedia: ${boolean}")
        if (boolean) {
            imgInstagram.visibility = View.VISIBLE
            imgTwitter.visibility = View.VISIBLE
            imgSnapchat.visibility = View.VISIBLE
            imgTiktok.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(activity, R.anim.scale)
            imgInstagram.startAnimation(animation)
            imgTwitter.startAnimation(animation)
            imgSnapchat.startAnimation(animation)
            imgTiktok.startAnimation(animation)
        } else {
            imgInstagram.visibility = View.GONE
            imgTwitter.visibility = View.GONE
            imgSnapchat.visibility = View.GONE
            imgTiktok.visibility = View.GONE
        }
    }


//
//    private val runnable = Runnable {
//        imageList.addAll(imageList)
//        titleList.addAll(titleList)
//        notifyDataSetChanged()
//    }
}