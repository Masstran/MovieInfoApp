package com.example.movieinfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (movieId in movieData.keys()) {
            when (movieId) {
                "movie1" -> setMovie(R.id.movie1, movieId)
                "movie2" -> setMovie(R.id.movie2, movieId)
                "movie3" -> setMovie(R.id.movie3, movieId)
            }
        }
    }

    private fun setMovie(id: Int, movieId: String) {
        val movie = findViewById<ConstraintLayout>(id)
        val thisMovieData: JSONObject = movieData.getJSONObject(movieId)
        val movieName = movie.getViewById(R.id.movie_name)
        if (movieName !is TextView)
            throw Exception("Incorrect type of movieName")
        movieName.text = thisMovieData.getString(movieNameKey)

        val movieDescription = movie.getViewById(R.id.movie_description)
        if (movieDescription !is TextView)
            throw Exception("Incorrect type of movieDescription")
        movieDescription.text = thisMovieData.getString(movieDescriptionKey)

        val detailsButton = movie.getViewById(R.id.details_button)
        if (detailsButton !is Button)
            throw Exception("Incorrect type of detailsButton")

        detailsButton.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("movieId", movieId)
            startActivity(intent)
        }
    }

    companion object {
        val movieData : JSONObject = JSONObject("""
            {
                "movie1": {
                    "movie_name": "Le Movie (2018)",
                    "movie_description": "This is a movie about something"
                },
                "movie2": {
                    "movie_name": "A movie about stuff (2016)",
                    "movie_description": "Definitely the best movie about stuff"
                },
                "movie3": {
                    "movie_name": "Doodely-doo (2020)",
                    "movie_description": "Doodles la doodles, lol"
                }
            }
        """.trimIndent())

        const val movieNameKey = "movie_name"
        const val movieDescriptionKey = "movie_description"

    }
}

