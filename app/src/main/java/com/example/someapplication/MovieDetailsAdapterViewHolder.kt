package com.example.someapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieDetailsAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val photo = itemView.findViewById<ImageView>(R.id.iv_actor_holder)
    val name = itemView.findViewById<TextView>(R.id.tv_actor_holder)
}