package com.example.someapplication.ui.movies

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.example.someapplication.R
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.database.movieslist.MoviesListEntity
import com.example.someapplication.service.MyWorker
import com.example.someapplication.ui.moviedetails.FragmentMovieDetails
import kotlinx.android.synthetic.main.fragment_movie_list.*
import java.util.concurrent.TimeUnit

class FragmentMoviesList : Fragment(), MoviesListAdapter.Callback {

    private var genreList = listOf<GenresEntity>()

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
        initObservers()
        initWorker()
        viewModel.getCachedGenres()
    }

    private fun initObservers() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, { movies ->
            movies ?: return@observe
            val moviesListAdapter = MoviesListAdapter(movies, genreList)
            moviesListAdapter.initCallback(this)
            rv_movie_list.adapter = moviesListAdapter

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                rv_movie_list.layoutManager = GridLayoutManager(context, 2)
            } else {
                rv_movie_list.layoutManager = GridLayoutManager(context, 4)
            }
        })

        viewModel.genresLiveData.observe(viewLifecycleOwner, {
            it ?: return@observe
            genreList = it
            viewModel.getCachedMovies()
        })
    }

    @SuppressLint("RestrictedApi")
    private fun initWorker() {
        val constr = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val uploadWork =
            PeriodicWorkRequest.Builder(MyWorker::class.java, 8, TimeUnit.HOURS)
                .setConstraints(constr)
                .addTag("MyWorker")
                .build()

        val workManager = WorkManager.getInstance(requireActivity()).enqueue(uploadWork)
        workManager.state.observe(viewLifecycleOwner, { state ->
            if (state == Operation.SUCCESS) {
                viewModel.getCachedGenres()
            }
        })
    }

    override fun startMovieDetailsFragment(item: MoviesListEntity) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, FragmentMovieDetails.newInstance(item.id))
            .addToBackStack(null)
            .commit()
    }
}