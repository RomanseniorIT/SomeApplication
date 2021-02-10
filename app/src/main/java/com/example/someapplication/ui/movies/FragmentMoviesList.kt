package com.example.someapplication.ui.movies

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.someapplication.R
import com.example.someapplication.data.database.movieslist.GenresEntity
import com.example.someapplication.data.database.movieslist.MoviesListEntity
import com.example.someapplication.service.MyWorker
import com.example.someapplication.ui.moviedetails.FragmentMovieDetails
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.flow.collectLatest
import java.util.concurrent.TimeUnit

class FragmentMoviesList : Fragment(), MoviesListAdapter.Callback {

    private var genreList = listOf<GenresEntity>()
    private var page = 1
    private val viewModel by viewModels<MoviesListViewModel>()

    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var moviesListAdapter: MoviesListAdapter

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
        initListeners()
        viewModel.getCachedGenres()
        gridLayoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager(context, 2)
        } else {
            GridLayoutManager(context, 4)
        }
        rv_movie_list.layoutManager = gridLayoutManager
    }

    private fun initListeners() {
        rv_movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
                if (isLastItemVisible()) {
                    viewModel.getCachedMovies(page)
                }
            }
        })
    }

    private fun initObservers() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, { movies ->
            movies ?: return@observe
            page++
        })

        viewModel.genresLiveData.observe(viewLifecycleOwner, {
            it ?: return@observe
            genreList = it
//            viewModel.getCachedMovies(page)
            moviesListAdapter = MoviesListAdapter(genreList)
            moviesListAdapter.initCallback(this)
            rv_movie_list.adapter = moviesListAdapter
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.movies.collectLatest { pagingData ->
                    moviesListAdapter.submitData(pagingData)
                }
            }
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
                .addTag(TAG)
                .build()

        val workManager = WorkManager.getInstance(requireActivity()).enqueue(uploadWork)
        workManager.state.observe(viewLifecycleOwner, { state ->
            if (state == Operation.SUCCESS) {
                viewModel.getCachedGenres()
            }
        })
    }

    private fun isLastItemVisible(): Boolean {
        val moviesAmount = moviesListAdapter.itemCount
        val visibleItemPosition = gridLayoutManager.findLastVisibleItemPosition()
        return moviesAmount <= visibleItemPosition+1
    }

    override fun startMovieDetailsFragment(item: MoviesListEntity) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, FragmentMovieDetails.newInstance(item.id))
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        private const val TAG = "MyWorker"
    }
}