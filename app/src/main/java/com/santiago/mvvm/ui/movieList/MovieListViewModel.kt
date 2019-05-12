package com.santiago.mvvm.ui.movieList


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.santiago.mvvm.data.Resource
import com.santiago.mvvm.data.local.dao.MovieDao
import com.santiago.mvvm.data.local.entity.MovieEntity
import com.santiago.mvvm.data.remote.api.MovieApiService
import com.santiago.mvvm.data.repository.MovieRepository
import com.santiago.mvvm.utils.AbsentLiveData

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

