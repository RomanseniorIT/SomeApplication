package com.example.someapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MovieDetailsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(item: Actor) {
        val photo = itemView.findViewById<ImageView>(R.id.iv_actor_holder)
        val name = itemView.findViewById<TextView>(R.id.tv_actor_holder)

        name.text = itemView.context.getString(item.name)
        val image = ContextCompat.getDrawable(itemView.context, item.photo)
        photo.setImageDrawable(image)
    }
}