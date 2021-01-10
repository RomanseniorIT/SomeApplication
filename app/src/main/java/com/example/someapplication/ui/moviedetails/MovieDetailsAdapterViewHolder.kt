package com.example.someapplication.ui.moviedetails

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.someapplication.R
import com.example.someapplication.data.model.Actor

class MovieDetailsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(item: Actor) {
        val photo = itemView.findViewById<ImageView>(R.id.iv_actor_holder)
        val name = itemView.findViewById<TextView>(R.id.tv_actor_holder)

        name.text = item.name
        if (item.picture != null) {
            val imageUrl = "https://image.tmdb.org/t/p/original/${item.picture}"
            Glide.with(itemView)
                .load(imageUrl)
                .placeholder(R.drawable.ic_download)
                .centerCrop()
                .into(photo)
        }
    }
}