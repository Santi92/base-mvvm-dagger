package com.santiago.mvvm.data.repository


import androidx.lifecycle.LiveData
import com.santiago.mvvm.AppExecutors
import com.santiago.mvvm.data.NetworkBoundResource
import com.santiago.mvvm.data.Resource
import com.santiago.mvvm.data.local.dao.MovieDao
import com.santiago.mvvm.data.local.entity.MovieEntity
import com.santiago.mvvm.data.remote.ApiResponse
import com.santiago.mvvm.data.remote.api.MovieApiService
import com.santiago.mvvm.data.remote.model.MovieApiResponse
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


    /*
     * We are using this method to fetch the movies list
     * NetworkBoundResource is part of the Android architecture
     * components. You will notice that this is a modified version of
     * that class. That class is based on LiveData but here we are
     * using Observable from RxJava.
     *
     * There are three methods called:
     * a. fetch data from server
     * b. fetch data from local
     * c. save data from api in local
     *
     * So basically we fetch data from server, store it locally
     * and then fetch data from local and update the UI with
     * this data.
     *
     * */
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





}