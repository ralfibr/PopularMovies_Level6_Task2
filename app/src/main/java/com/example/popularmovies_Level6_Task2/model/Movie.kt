package com.example.popularmovies_Level6_Task2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("backdrop_path") var backdrop: String,
    @SerializedName("poster_path") var poster: String,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var date: String,
    @SerializedName("vote_average") var rating: Float,
    @SerializedName("overview") var overview: String
) : Parcelable