package com.example.movieinfoapp

import org.json.JSONObject

data class MovieItem(val name: String, val description: String, var comment: String, var isFavorite: Boolean) {
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

        fun
    }
}