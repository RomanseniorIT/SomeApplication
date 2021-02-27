package com.example.someapplication.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.someapplication.R
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.database.movieslist.MoviesListEntity

//class MoviesListAdapter(val genres: List<GenresEntity>) :
//    RecyclerView.Adapter<MoviesListAdapterViewHolder>() {
//
//    lateinit var callback: Callback
//    private val moviesList = mutableListOf<MoviesListEntity>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListAdapterViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_movie, parent, false)
//        return MoviesListAdapterViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return moviesList.size
//    }
//
//    override fun onBindViewHolder(holder: MoviesListAdapterViewHolder, position: Int) {
//        holder.onBind(moviesList[position], genres, this.callback)
//    }
//
//    fun initCallback(callback: Callback) {
//        this.callback = callback
//    }
//
//    fun addItems(moviesList: List<MoviesListEntity>){
//        this.moviesList.addAll(moviesList)
//        notifyDataSetChanged()
//    }
//
//    interface Callback {
//        fun startMovieDetailsFragment(item: MoviesListEntity)
//    }
//}

class MoviesListAdapter(val genres: List<GenresEntity>) :
    PagingDataAdapter<MoviesListEntity, MoviesListAdapterViewHolder>(MoviesComparator) {

    lateinit var callback: Callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_movie, parent, false)
        return MoviesListAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesListAdapterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it, genres, this.callback) }
    }

    fun initCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun startMovieDetailsFragment(item: MoviesListEntity, view: View)
    }

    object MoviesComparator : DiffUtil.ItemCallback<MoviesListEntity>() {
        override fun areItemsTheSame(oldItem: MoviesListEntity, newItem: MoviesListEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MoviesListEntity, newItem: MoviesListEntity) =
            oldItem == newItem
    }
}