package com.jundi.infopilem.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jundi.infopilem.R
import com.jundi.infopilem.adapters.MovieItemClickListener
import com.jundi.infopilem.adapters.SearchAdapter
import com.jundi.infopilem.movieList.data.remote.respond.MovieDto
import com.jundi.infopilem.viewModels.MovieViewModel

class SearchActivity : AppCompatActivity(), MovieItemClickListener {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var loadingViewSearch: ProgressBar
    private lateinit var listErrorSearch: TextView
    private lateinit var listEmptySearch: TextView
    private lateinit var searchView: SearchView
    private lateinit var searchAdapter: SearchAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val query = intent.getStringExtra("SEARCH_QUERY")

        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        if (query != null) {
            movieViewModel.searchMovies(query,1)
        }

        recyclerViewSearch = findViewById(R.id.movieListSearch)

        recyclerViewSearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadingViewSearch = findViewById(R.id.loadingViewSearch)
        listErrorSearch = findViewById(R.id.listErrorSearch)
        listEmptySearch = findViewById(R.id.listEmptySearch)
        searchView = findViewById(R.id.searchView)


        movieViewModel.searchResults.observe(this) { searchResults ->

            loadingViewSearch.visibility = View.VISIBLE

            if (searchResults != null) {

                loadingViewSearch.visibility = View.GONE

                if (searchResults.isNotEmpty()) {

                    searchAdapter =
                        SearchAdapter(this, searchResults,this)
                    recyclerViewSearch.adapter = searchAdapter


                    recyclerViewSearch.visibility = View.VISIBLE

                    listErrorSearch.visibility = View.GONE

                    listEmptySearch.visibility = View.GONE
                } else {

                    listEmptySearch.visibility = View.VISIBLE

                    recyclerViewSearch.visibility = View.GONE

                    listErrorSearch.visibility = View.GONE
                }
            } else {
                listErrorSearch.visibility = View.VISIBLE

                recyclerViewSearch.visibility = View.GONE

                loadingViewSearch.visibility = View.GONE

                listEmptySearch.visibility = View.GONE
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    movieViewModel.searchMovies(query, 1)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    movieViewModel.searchMovies(newText, 1)
                }
                return true
            }
        })
    }

    override fun onMovieClick(movie: MovieDto, movieImageView: ImageView) {

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("imgURL", movie.getFullPosterPath())
        intent.putExtra("imgCover", movie.getFullBackdropPath())
        intent.putExtra("id", movie.id)

        val options = ActivityOptions.makeSceneTransitionAnimation(this, movieImageView, "sharedName")
        startActivity(intent, options.toBundle())
    }

}