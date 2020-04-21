package com.sundevs.core.data.remote.api

import androidx.lifecycle.LiveData

import com.sundevs.core.data.local.entity.MovieEntity
import com.sundevs.core.data.remote.ApiResponse
import com.sundevs.core.data.remote.model.MovieApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/popular?language=en-US&region=US&page=1")
    fun fetchMoviesByType(): LiveData<ApiResponse<MovieApiResponse>>

    @GET("/3/movie/{movieId}")
    fun fetchMovieDetail(@Path("movieId") movieId: String): LiveData<ApiResponse<MovieEntity>>
}