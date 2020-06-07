package com.example.popularmovies_Level6_Task2.ui.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies_Level6_Task2.api.MovieRepository
import com.example.popularmovies_Level6_Task2.api.MovieResult
import com.example.popularmovies_Level6_Task2.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/**
 * @author Raeef Ibrahim
 * 500766393
 */
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository = MovieRepository()
    val movie = MutableLiveData<List<Movie>>()
    val error = MutableLiveData<String>()

    /**
     * Get movie data from the repository using Retrofit.
     * onResponse if the response is successful populate the [movie] object.
     * If the call encountered an error then populate the [error] object.
     */
    fun getCorrectMovies(year: String) {
        moviesRepository.getMovies(year).enqueue(object : Callback<MovieResult> {

            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                if (response.isSuccessful) {
                    movie.value = response.body()?.resultMovie
                } else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}
