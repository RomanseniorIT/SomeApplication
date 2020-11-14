package com.example.someapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startDetailsActivityButton = findViewById<Button>(R.id.start_deatails_activity)

        startDetailsActivityButton.setOnClickListener {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
