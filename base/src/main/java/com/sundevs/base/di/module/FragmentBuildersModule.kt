package com.sundevs.base.di.module


import com.sundevs.detailmoviefeature.MovieDetailFragment
import com.sundevs.movielistfeature.MovieListFragment
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