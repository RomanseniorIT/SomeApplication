package com.example.someapplication.ui.moviedetails

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.someapplication.BuildConfig
import com.example.someapplication.R
import com.example.someapplication.data.database.moviedetails.ActorsEntity

class MovieDetailsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(item: ActorsEntity) {
        val photo = itemView.findViewById<ImageView>(R.id.iv_actor_holder)
        val name = itemView.findViewById<TextView>(R.id.tv_actor_holder)

        name.text = item.name
        item.picture?.let {
            val imageUrl = "${BuildConfig.BASE_IMAGE_URL}$it"
            Glide.with(itemView)
                .load(imageUrl)
                .placeholder(R.drawable.ic_download)
                .centerCrop()
                .into(photo)
        }
    }
}