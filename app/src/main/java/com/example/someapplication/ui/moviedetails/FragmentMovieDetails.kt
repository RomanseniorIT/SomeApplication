package com.example.someapplication.ui.moviedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.someapplication.R
import com.example.someapplication.data.database.moviedetails.ActorsEntity
import com.example.someapplication.data.database.moviedetails.MovieDetailsEntity
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.model.Actor
import com.example.someapplication.data.model.Genre
import com.example.someapplication.data.model.MovieFull
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FragmentMovieDetails : Fragment() {

    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieId = arguments?.getInt(MOVIE_ID)

        initListeners()
        initObservers()

        movieId?.let {
            viewModel.getCachedMovie(it)
        }
    }

    private fun initObservers() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, {
            if (it == null) {
                Toast.makeText(
                    context,
                    "Check internet connection and restart",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                bind(it.movieFull, it.actors)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    fun bind(movie: MovieDetailsEntity?, actors: List<ActorsEntity>?) {
        if (movie != null) {
            setRate(movie.ratings)
            val posterUrl = "https://image.tmdb.org/t/p/original/${movie.backdrop}"
            Glide.with(requireActivity())
                .load(posterUrl)
                .placeholder(R.drawable.ic_download)
                .centerCrop()
                .into(iv_header)
            tv_age.text = if (movie.minimumAge) "+16" else "+13"
            tv_title.text = movie.title
            tv_reviews.text = "${movie.numberOfRatings} reviews"
            tv_storyline_description.text = movie.overview
            val genres = Json.decodeFromString<List<Genre>>(movie.genres)
            tv_genre.text = setGenres(genres)

            val movieDetailsAdapter = MovieDetailsAdapter(actors!!)
            rv_actors.adapter = movieDetailsAdapter
            val linearLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rv_actors.layoutManager = linearLayoutManager
            val space = getString(R.string.rv_space)
            val itemDecorator = HorizontalSpaceItemDecorator(space)
            rv_actors.addItemDecoration(itemDecorator)
        }
    }

    private fun setGenres(genres: List<Genre>): String {
        var genresStr = ""
        for (i in genres.indices) {
            genresStr += if (i == genres.size - 1) {
                genres[i].name
            } else {
                "${genres[i].name}, "
            }
        }
        return genresStr
    }

    private fun setRate(rate: Float) {
        if (rate >= 2.0F) {
            setRedStar(iv_star_one)
        } else {
            setGrayStar(iv_star_one)
        }

        if (rate >= 4.0F) {
            setRedStar(iv_star_two)
        } else {
            setGrayStar(iv_star_two)
        }

        if (rate >= 6.0F) {
            setRedStar(iv_star_three)
        } else {
            setGrayStar(iv_star_three)
        }

        if (rate >= 8.0F) {
            setRedStar(iv_star_four)
        } else {
            setGrayStar(iv_star_four)
        }

        if (rate == 10.0F) {
            setRedStar(iv_star_five)
        } else {
            setGrayStar(iv_star_five)
        }

        if (rate > 10)
            throw IllegalStateException("Illegal rating value")
    }

    private fun setGrayStar(starView: ImageView) {
        starView.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.ic_gray_star
            )
        )
    }

    private fun setRedStar(starView: ImageView) {
        starView.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.ic_red_star
            )
        )
    }

    private fun initListeners() {
        iv_back_arrow.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    companion object {
        private const val MOVIE_ID = "movie"
        fun newInstance(moviePreview: Int): FragmentMovieDetails {
            val fragment = FragmentMovieDetails()
            val args = Bundle()
            args.putInt(MOVIE_ID, moviePreview)
            fragment.arguments = args
            return fragment
        }
    }
}
