package com.example.movieinfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Exception

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val shareButton = findViewById<Button>(R.id.share_button)

        val movieId = intent.getStringExtra("movieId") ?: throw Exception("Null movieId came into DetailsActivity")

        val movieData = MainActivity.movieData.getJSONObject(movieId)

        val movieName = findViewById<TextView>(R.id.movie_name)
        movieName.text = movieData.getString(MainActivity.movieNameKey)

        val movieDescription = findViewById<TextView>(R.id.movie_description)
        movieDescription.text = movieData.getString(MainActivity.movieDescriptionKey)

        shareButton.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Let's go watch movies!")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(intent, null)
            startActivity(shareIntent)
        }
    }
}