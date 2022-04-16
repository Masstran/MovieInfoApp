package com.example.movieinfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovie(R.id.movie1, movie_data.getJSONObject(0))
        setMovie(R.id.movie2, movie_data.getJSONObject(1))
        setMovie(R.id.movie3, movie_data.getJSONObject(2))
    }

    private fun setMovie(id: Int, movie_data: JSONObject) {
        val movie = findViewById<ConstraintLayout>(id)

        val movieName = movie.getViewById(R.id.movie_name)
        if (movieName !is TextView)
            throw Exception("Incorrect type of movieName")
        movieName.text = movie_data.getString(movie_name_key)

        val movieDescription = movie.getViewById(R.id.movie_description)
        if (movieDescription !is TextView)
            throw Exception("Incorrect type of movieDescription")
        movieDescription.text = movie_data.getString(movie_description_key)

        val detailsButton = movie.getViewById(R.id.details_button)
        if (detailsButton !is Button)
            throw Exception("Incorrect type of detailsButton")

        detailsButton.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        val movie_data : JSONArray = JSONArray("""
            [{
                "id": "movie1",
                "movie_name": "Le Movie (2018)",
                "movie_description": "This is a movie about something"
             },
             {
                "id": "movie2",
                "movie_name": "A movie about stuff (2016)",
                "movie_description": "Definitely the best movie about stuff"
             },
             {
                "id": "movie3",
                "movie_name": "Doodely-doo (2020)",
                "movie_description": "Doodles la doodles, lol"
             }]
        """.trimIndent())

        val movie_name_key = "movie_name"
        val movie_description_key = "movie_description"

    }
}

