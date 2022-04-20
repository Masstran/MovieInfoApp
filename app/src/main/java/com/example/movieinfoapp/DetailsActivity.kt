package com.example.movieinfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
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
                putExtra(Intent.EXTRA_TEXT, "Let's go watch movies! I heard ${movieName.text} is good!")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(intent, null)
            startActivity(shareIntent)
        }

        val result = Intent()

        val likeCheckBox = findViewById<CheckBox>(R.id.like_marker)
        likeCheckBox.setOnClickListener {
            val like = !result.getBooleanExtra(LIKE_VALUE_KEY, false)
            result.putExtra(LIKE_VALUE_KEY, like)
            setResult(RESULT_OK, result)
        }

        val commentBox = findViewById<EditText>(R.id.movie_comment)
        commentBox.doAfterTextChanged {
            result.putExtra(COMMENT_KEY, it.toString())
            setResult(RESULT_OK, result)
        }
    }

    companion object {
        const val LIKE_VALUE_KEY = "like"
        const val COMMENT_KEY = "comment"
    }
}