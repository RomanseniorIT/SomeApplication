package com.example.someapplication.ui.moviedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.someapplication.R
import com.example.someapplication.data.Genre
import com.example.someapplication.data.Movie
import kotlinx.android.synthetic.main.fragment_movie_details.*

class FragmentMovieDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movie = arguments?.getParcelable<Movie>(MOVIE_KEY)
        bind(movie)
        initListeners()
    }

    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie?){
        setRate(movie!!.ratings)
        Glide.with(requireActivity())
            .load(movie.backdrop)
            .placeholder(R.drawable.ic_download)
            .centerCrop()
            .into(iv_header)

        tv_age.text = movie.minimumAge.toString()
        tv_title.text = movie.title
        tv_reviews.text = "${movie.numberOfRatings} reviews"
        tv_storyline_description.text = movie.overview
        tv_genre.text = setGenres(movie.genres)

        val movieDetailsAdapter = MovieDetailsAdapter(movie.actors)
        rv_actors.adapter = movieDetailsAdapter
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_actors.layoutManager = linearLayoutManager
        val space = getString(R.string.rv_space)
        val itemDecorator = HorizontalSpaceItemDecorator(space)
        rv_actors.addItemDecoration(itemDecorator)

    }

    private fun setGenres(genres: List<Genre>): String{
        var genresStr = ""
        for (i in genres.indices){
            genresStr += if (i == genres.size-1){
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
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: Movie?): FragmentMovieDetails {
            val fragment = FragmentMovieDetails()
            val args = Bundle()
            args.putParcelable(MOVIE_KEY, movie)
            fragment.arguments = args
            return fragment
        }
    }
}
