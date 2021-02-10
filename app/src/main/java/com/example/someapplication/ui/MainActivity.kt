package com.example.someapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.someapplication.R
import com.example.someapplication.ui.moviedetails.FragmentMovieDetails
import com.example.someapplication.ui.movies.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FragmentMoviesList())
                .addToBackStack(null)
                .commit()
        }

        intent?.let {
            handleIntent(it)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            handleIntent(it)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun handleIntent(intent: Intent) {
        if(intent.action == Intent.ACTION_VIEW){
            val movieId = intent.data?.lastPathSegment?.toIntOrNull()
            movieId?.let {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, FragmentMovieDetails.newInstance(it))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}