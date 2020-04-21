package com.sundevs.core.data.repository

import androidx.lifecycle.LiveData
import com.sundevs.core.AppExecutors
import com.sundevs.core.data.NetworkBoundResource
import com.sundevs.core.data.Resource
import com.sundevs.core.data.local.dao.MovieDao
import com.sundevs.core.data.local.entity.MovieEntity
import com.sundevs.core.data.remote.ApiResponse
import com.sundevs.core.data.remote.api.MovieApiService
import com.sundevs.core.data.remote.model.MovieApiResponse

import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val movieDao: MovieDao,
    private val movieApiService: MovieApiService
) {

    fun loadMoviesByType(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MovieApiResponse>(appExecutors) {
            override fun saveCallResult(item: MovieApiResponse) {
                movieDao.insertMovies(item.results)
            }

            override fun shouldFetch(data: List<MovieEntity>?)= data == null


            override fun loadFromDb(): LiveData<List<MovieEntity>> {
                return movieDao.getMoviesByPage()
            }

            override fun createCall()= movieApiService.fetchMoviesByType()


        }.asLiveData()
    }

    fun fetchMovieDetails(movieId: Long): LiveData<Resource<MovieEntity>> {

        return object : NetworkBoundResource<MovieEntity, MovieEntity>(appExecutors) {
            override fun saveCallResult(item: MovieEntity) {
                val movieEntity: MovieEntity? = movieDao.getMovieById(movieId).value
                if(null == movieEntity){
                    movieDao.insertMovie(item)
                }
            }

            override fun shouldFetch(data: MovieEntity?)= data == null

            override fun loadFromDb(): LiveData<MovieEntity> {
                return movieDao.getMovieById(movieId)
            }

            override fun createCall(): LiveData<ApiResponse<MovieEntity>> {
                val id = movieId.toString()
                return movieApiService.fetchMovieDetail(id)
            }


        }.asLiveData()
    }

}