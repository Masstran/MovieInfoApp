package com.example.movieinfoapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val movieNameTV: TextView = itemView.findViewById(R.id.movie_name)
    private val movieDescriptionTV: TextView = itemView.findViewById(R.id.movie_description)
    private val moviePosterIV: ImageView = itemView.findViewById(R.id.movie_poster)
    private val likeMarkerIV: ImageView = itemView.findViewById(R.id.like_marker)
    private val commentMarkerIV: ImageView = itemView.findViewById(R.id.comment_marker)


}