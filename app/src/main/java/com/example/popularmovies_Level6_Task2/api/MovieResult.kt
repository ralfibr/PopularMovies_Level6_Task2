package com.example.popularmovies_Level6_Task2.api

import com.example.popularmovies_Level6_Task2.model.Movie
import com.google.gson.annotations.SerializedName

class MovieResult(
    @SerializedName("results") var resultMovie: List<Movie>
)