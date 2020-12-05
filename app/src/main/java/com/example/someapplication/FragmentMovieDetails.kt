package com.example.someapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMovieDetails : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.rv_actors)
        val itemDecorator = HorizontalSpaceItemDecorator(25)
        recyclerView.addItemDecoration(itemDecorator)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val backButton = view.findViewById<ImageView>(R.id.iv_back_arrow)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val actors = arguments?.getParcelableArrayList<Actor>(ACTOR_LIST_KEY)
        val movieDetailsAdapter = MovieDetailsAdapter(actors)
        recyclerView.adapter = movieDetailsAdapter
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager

    }

    companion object {
        private const val ACTOR_LIST_KEY = "actors"
        fun newInstance(actors: ArrayList<Actor>?): FragmentMovieDetails {
            val fragment = FragmentMovieDetails()
            val args = Bundle()
            args.putParcelableArrayList(ACTOR_LIST_KEY, actors)
            fragment.arguments = args
            return fragment
        }
    }
}
