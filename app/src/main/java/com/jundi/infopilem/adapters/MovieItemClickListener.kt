package com.jundi.infopilem.adapters

import android.widget.ImageView
import com.jundi.infopilem.movieList.data.remote.respond.MovieDto

interface MovieItemClickListener {
    fun onMovieClick(movie: MovieDto, movieImageView: ImageView)
}