package com.santiago.mvvm.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.santiago.mvvm.factory.ViewModelFactory

import com.santiago.mvvm.viewmodel.MovieListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class ViewModelModule {
    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  MovieListViewModel.class as key,
     * and a Provider that will build a MovieListViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    protected abstract fun movieListViewModel(moviesListViewModel: MovieListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}