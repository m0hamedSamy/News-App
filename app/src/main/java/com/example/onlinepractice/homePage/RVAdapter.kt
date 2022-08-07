package com.example.onlinepractice.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinepractice.R

class RVAdapter(val dataSet: Array<Item>) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>(){
    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val tvText: TextView
        val ivImage: ImageView

        init {
            tvText = view.findViewById(R.id.tv_RVItemText)
            ivImage = view.findViewById(R.id.iv_RVItemImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.homepage_rv_item_layout, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.tvText.text = dataSet[position].text
        val imgSrc = dataSet[position].imgSrc

        Glide
            .with(holder.itemView)
            .load(imgSrc)
            .into(holder.ivImage)
    }

    override fun getItemCount() = dataSet.size
}

data class Item(val text: String, val imgSrc: String)