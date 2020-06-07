package com.example.popularmovies_Level6_Task2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularmovies_Level6_Task2.R
import com.example.popularmovies_Level6_Task2.model.Movie
import com.example.popularmovies_Level6_Task2.ui.adapter.MovieAdapter
import com.example.popularmovies_Level6_Task2.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * @author Raeef Ibrahim
 * 500766393
 */
const val DETAIL_MOVIE = "DETAIL_MOVIE"

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies) { movie -> displayMovieDetails(movie) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()

    }

    private fun initViews() {
        rvMovies.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvMovies.adapter = movieAdapter

        btnSubmit.setOnClickListener { displayMovies() }
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.movie.observe(this, Observer { movies ->
            if (movies != null) {
                this@MainActivity.movies.clear()
                this@MainActivity.movies.addAll(movies)
                movieAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun displayMovies() {

        val yearRange = 1895..2022
        val year = etYear.text.toString()

        when {
            year.isBlank() -> {
                inputInvalidMessage(getString(R.string.blank_year))
                return
            }
            year.toInt() !in yearRange -> {
                inputInvalidMessage(getString(R.string.invalid_year))
                return
            }
            else -> {
                mainActivityViewModel.getCorrectMovies(year)

            }
        }

    }

    private fun displayMovieDetails(movie: Movie) {
        val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
        intent.putExtra(DETAIL_MOVIE, movie)
        startActivity(intent)
    }

    private fun inputInvalidMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
