package com.example.popularmovies_Level6_Task2.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.popularmovies_Level6_Task2.R
import com.example.popularmovies_Level6_Task2.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_movie_detail.*

/**
 * @author Raeef Ibrahim
 * 500766393
 */
class MovieDetailActivity : AppCompatActivity() {

    private val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.movie_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews() {
        val movie: Movie = intent.getParcelableExtra(DETAIL_MOVIE)
        Glide.with(this).load(imageBaseUrl + movie.backdrop).into(ivBackdrop)
        Glide.with(this).load(imageBaseUrl + movie.poster).into(ivPoster)

        tvTitle.text = movie.title
        tvReleaseDate.text = this.getString(R.string.release_date, movie.date)
        tvRating.text = movie.rating.toString()
        tvOverview.text = movie.overview
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
