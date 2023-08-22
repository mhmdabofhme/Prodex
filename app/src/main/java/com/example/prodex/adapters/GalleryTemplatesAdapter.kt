package com.example.prodex.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prodex.R
import com.example.prodex.databinding.CustomTemplateBinding
import java.util.*


class GalleryTemplatesAdapter(
    private val activity: Activity,
//    private val list: MutableList<Post>,
//    private var optionsMenuClickListener: OptionsMenuClickListener
) :
    RecyclerView.Adapter<GalleryTemplatesAdapter.MyViewHolder>() {

    private val list =
        listOf<Int>(
            R.drawable.temp1,
            R.drawable.temp2,
            R.drawable.temp3,
            R.drawable.temp4
        )

    inner class MyViewHolder(b: CustomTemplateBinding) :
        RecyclerView.ViewHolder(b.root) {
        val binding: CustomTemplateBinding = b
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val b: CustomTemplateBinding =
            CustomTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(b)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        with(holder) {
            val data = list[position]
            binding.root.setImageResource(data)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}