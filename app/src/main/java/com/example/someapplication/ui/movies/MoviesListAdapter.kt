package com.example.someapplication.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.someapplication.data.Movie
import com.example.someapplication.R

class MoviesListAdapter(val moviesList: List<Movie>) :
    RecyclerView.Adapter<MoviesListAdapterViewHolder>() {

    lateinit var callback: Callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_movie, parent, false)
        return MoviesListAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MoviesListAdapterViewHolder, position: Int) {
        holder.onBind(moviesList[position], this.callback)
    }

    fun initCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun startMovieDetailsFragment(item: Movie)
    }
}