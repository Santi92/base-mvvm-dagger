package com.santiago.mvvm.di.module

import com.santiago.mvvm.ui.detail.MovieDetailFragment
import com.santiago.mvvm.ui.movieList.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    /*
        * We define the name of the Fragment we are going
        * to inject the ViewModelFactory into. i.e. in our case
        * The name of the fragment: MovieListFragment
        */
    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment

}