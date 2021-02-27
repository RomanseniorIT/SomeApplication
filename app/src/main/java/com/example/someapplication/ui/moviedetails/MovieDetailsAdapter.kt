package com.example.someapplication.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.someapplication.R
import com.example.someapplication.data.database.moviedetails.ActorsEntity
import com.example.someapplication.data.model.Actor

class MovieDetailsAdapter(private val items: List<ActorsEntity>) :
    RecyclerView.Adapter<MovieDetailsAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailsAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        return MovieDetailsAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieDetailsAdapterViewHolder, position: Int) {
        holder.onBind(items[position])
    }
}