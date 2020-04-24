package com.santiago.mvvm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel;
import com.sundevs.core.data.Resource
import com.sundevs.core.data.local.entity.MovieEntity

import com.sundevs.core.data.repository.MovieRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(movieRepository: MovieRepository): ViewModel() {


    private val movieId = MutableLiveData<Long>()

    val movie: LiveData<Resource<MovieEntity>> = Transformations
        .switchMap(movieId){
            movieRepository.fetchMovieDetails(it)
        }

    fun loadMovie(id: Long) {
        movieId.value = id
    }
}