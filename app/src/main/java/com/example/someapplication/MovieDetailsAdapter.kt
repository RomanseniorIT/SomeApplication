package com.example.someapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MovieDetailsAdapter(val items: ArrayList<Actor>?): RecyclerView.Adapter<MovieDetailsAdapterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailsAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor,parent, false)
        return MovieDetailsAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: MovieDetailsAdapterViewHolder, position: Int) {
        val item = items?.get(position)

        holder.name.text = holder.itemView.context.getString(item!!.name)

        val photo = ContextCompat.getDrawable(holder.itemView.context, item.photo)
        holder.photo.setImageDrawable(photo)
    }
}