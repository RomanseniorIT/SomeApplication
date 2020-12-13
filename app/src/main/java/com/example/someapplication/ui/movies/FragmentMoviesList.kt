package com.example.someapplication.ui.movies

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.someapplication.R
import com.example.someapplication.data.Actor
import com.example.someapplication.data.Movie
import com.example.someapplication.ui.moviedetails.FragmentMovieDetails

class FragmentMoviesList : Fragment(), MoviesListAdapter.Callback {


    private val moviesList = mutableListOf<Movie>()

    private val viewModel by viewModels<MoviesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getMovies(requireActivity())

        viewModel.items.observe(viewLifecycleOwner, {
            val recyclerView = view.findViewById<RecyclerView>(R.id.rv_movie_list)
            val moviesListAdapter = MoviesListAdapter(it)
            moviesListAdapter.initCallback(this)
            recyclerView.adapter = moviesListAdapter

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerView.layoutManager = GridLayoutManager(context, 2)
            } else {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
            }
        })

    }

    override fun startMovieDetailsFragment(item: Movie) {

        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, FragmentMovieDetails.newInstance(item))
            ?.addToBackStack(null)
            ?.commit()
    }
}