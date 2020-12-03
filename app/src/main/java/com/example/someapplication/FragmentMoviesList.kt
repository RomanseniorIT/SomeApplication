package com.example.someapplication

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment(), MoviesListAdapter.Callback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_movie_list)
        val moviesListAdapter = MoviesListAdapter()
        moviesListAdapter.initCallback(this)
        recyclerView.adapter = moviesListAdapter

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }


    }

    override fun startMovieDetailsFragment() {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, FragmentMovieDetails())
            ?.addToBackStack(null)
            ?.commit()
    }
}