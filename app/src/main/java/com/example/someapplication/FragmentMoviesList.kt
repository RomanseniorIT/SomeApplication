package com.example.someapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val movieItem = view.findViewById<View>(R.id.movie_item)
        movieItem.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, FragmentMovieDetails())
                ?.addToBackStack(null)
                ?.commit()
        }

    }
}