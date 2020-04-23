package com.sundevs.movielistfeature


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

import com.sundevs.core.data.Resource
import com.sundevs.core.data.local.entity.MovieEntity
import com.sundevs.core.data.repository.MovieRepository

import javax.inject.Inject


/*
 * We are injecting the MovieDao class
 * and the MovieApiService class to the ViewModel.
 * */
class MovieListViewModel @Inject constructor(
    movieRepository: MovieRepository) : ViewModel() {

    /*
     * Method called by UI to fetch movies list
     * */
    private val reload = MutableLiveData<Boolean>()
    val listMovie: LiveData<Resource<List<MovieEntity>>> = Transformations
        .switchMap(reload){
            movieRepository.loadMoviesByType()
        }

    fun reload(value: Boolean) {
        reload.value = value
    }
}

