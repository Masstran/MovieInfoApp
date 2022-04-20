package com.example.movieinfoapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var highlightedMovieId: Int = 0
        set(value) {
            val oldView = findViewById<ConstraintLayout>(field)
            val oldText = oldView?.findViewById<TextView>(R.id.movie_name)
            oldText?.isSelected = false
            val view = findViewById<ConstraintLayout>(value)
            val newText = view.findViewById<TextView>(R.id.movie_name)
            newText.isSelected = true
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        highlightedMovieId = R.id.movie1
        savedInstanceState?.let { handleState(it) }

        for (movieId in movieData.keys()) {
            when (movieId) {
                "movie1" -> setMovie(R.id.movie1, movieId)
                "movie2" -> setMovie(R.id.movie2, movieId)
                "movie3" -> setMovie(R.id.movie3, movieId)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(highlightedMovieIdKey, highlightedMovieId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        handleState(savedInstanceState)
    }

    fun handleState(savedInstanceState: Bundle) {
        highlightedMovieId = savedInstanceState.getInt(highlightedMovieIdKey)
    }

    private fun setMovie(id: Int, movieId: String) {
        val movie = findViewById<ConstraintLayout>(id)
        val thisMovieData: JSONObject = movieData.getJSONObject(movieId)
        val movieName = movie.findViewById<TextView>(R.id.movie_name)
        Log.d("MovieInfoApp",thisMovieData.toString())
        movieName.text = thisMovieData.getString(movieNameKey)

        val movieDescription = movie.findViewById<TextView>(R.id.movie_description)
        movieDescription.text = thisMovieData.getString(movieDescriptionKey)

        val detailsButton = movie.getViewById(R.id.details_button)
        if (detailsButton !is Button)
            throw Exception("Incorrect type of detailsButton")

        detailsButton.setOnClickListener {
            highlightedMovieId = id
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("movieId", movieId)
            startActivityForResult(intent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                if (data!!.hasExtra(DetailsActivity.COMMENT_KEY)) {
                    val comment = data.getStringExtra(DetailsActivity.COMMENT_KEY)
//                    val commentMarker = findViewById<ImageView>(R.id.comment_marker)
                    Log.d("MovieInfoApp", "Comment is $comment")
                }
                if (data.hasExtra(DetailsActivity.LIKE_VALUE_KEY)) {
                    val like = data.getBooleanExtra(DetailsActivity.LIKE_VALUE_KEY, false)
//                    val likeMarker = findViewById<CheckBox>(R.id.like_marker)
//                    likeMarker.isActivated = like
                    Log.d("MovieInfoApp", "Like is $like")
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
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
        const val highlightedMovieIdKey = "highlightedMovieId"

    }
}

