package com.example.popularmovies_Level6_Task2.api

import retrofit2.Call

class MovieRepository {
    private val moviesApi: MovieApiService = MovieApi.createApi()

    fun getMovies(year: String): Call<MovieResult> = moviesApi.getCorrectMovies(year)
}