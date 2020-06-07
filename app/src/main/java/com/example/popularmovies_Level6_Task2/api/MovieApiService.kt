package com.example.popularmovies_Level6_Task2.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(
        "/3/discover/movie?" +
                "api_key=f81fa58028c1735b492909b0137b6f7c" +
                "&language=en-US" +
                "&sort_by=popularity.desc" +
                "&include_adult=false" +
                "&include_video=false" +
                "&page=1"
    )
    fun getCorrectMovies(@Query("year") year: String): Call<MovieResult>
}