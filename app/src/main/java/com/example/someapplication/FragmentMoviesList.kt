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


    private val avengersActors = arrayListOf(
        Actor(R.string.ironman, R.drawable.ic_ironman),
        Actor(R.string.cap, R.drawable.ic_cap),
        Actor(R.string.hulk, R.drawable.ic_hulk),
        Actor(R.string.thor, R.drawable.ic_thor)
    )

    private val moviesList =
        mutableListOf(
            Movie(
                1,
                "Avengers: End Game",
                "Action, Adventure, Drama",
                125,
                4,
                137,
                13,
                false,
                R.drawable.ic_card_background,
                avengersActors
            ),
            Movie(
                2,
                "Tenet",
                "Action, Sci-Fi, Thriller",
                98,
                5,
                97,
                16,
                false,
                R.drawable.ic_tenet_poster,
                arrayListOf()
            ),
            Movie(
                3,
                "Black Widow",
                "Action, Adventure, Sci-Fi",
                38,
                4,
                102,
                13,
                false,
                R.drawable.ic_widow_poster,
                arrayListOf()
            ),
            Movie(
                4,
                "Wonder Woman 1984",
                "Action, Adventure, Fantasy",
                74,
                5,
                120,
                13,
                false,
                R.drawable.ic_woman_poster,
                arrayListOf()
            )
        )

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
        val moviesListAdapter = MoviesListAdapter(moviesList)
        moviesListAdapter.initCallback(this)
        recyclerView.adapter = moviesListAdapter

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }

    }

    override fun startMovieDetailsFragment(id: Int) {

        val movie = moviesList.firstOrNull { it.id == id }
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, FragmentMovieDetails.newInstance(movie?.actors))
            ?.addToBackStack(null)
            ?.commit()
    }
}