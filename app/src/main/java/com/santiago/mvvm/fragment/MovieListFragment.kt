package com.santiago.mvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.mvvm.R
import com.santiago.mvvm.activity.MainActivity
import com.santiago.mvvm.adapter.MoviesListAdapter
import com.santiago.mvvm.custom.PagerSnapHelper
import com.santiago.mvvm.custom.RecyclerSnapItemListener
import com.santiago.mvvm.data.local.entity.MovieEntity
import com.santiago.mvvm.databinding.FragmentMovieListBinding
import com.santiago.mvvm.viewmodel.MovieListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieListFragment: Fragment(){

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var moviesListViewModel: MovieListViewModel

    /*
    * DataBinding es esencial
    * * */
    private lateinit var binding: FragmentMovieListBinding


    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        initialiseViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
    }

    private fun initialiseView() {
        moviesListAdapter = MoviesListAdapter(requireActivity())
        binding.moviesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.moviesList.adapter = moviesListAdapter

        val startSnapHelper = PagerSnapHelper(
            object : RecyclerSnapItemListener {
                override fun onItemSnap(position: Int) {
                    val movie = moviesListAdapter.getItem(position)
                    (requireActivity() as MainActivity).updateBackground(movie.getFormattedPosterPath())
                }
            }
        )
        startSnapHelper.attachToRecyclerView(binding.moviesList)
    }


    private fun initialiseViewModel() {
        moviesListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        moviesListViewModel.getMoviesLiveData().observe(this, Observer { resource ->
            if (resource!!.isLoading) {
                displayLoader()

            } else if (resource.data != null && !resource.data.isEmpty()) {
                updateMoviesList(resource.data)

            } else
                handleErrorResponse()
        })
        /* Fetch movies list  */
        moviesListViewModel.loadMoreMovies()
    }


    private fun displayLoader() {
        binding.moviesList.visibility = View.GONE
        binding.loaderLayout.rootView.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.moviesList.visibility = View.VISIBLE
        binding.loaderLayout.rootView.visibility = View.GONE
    }

    private fun updateMoviesList(movies: List<MovieEntity>) {
        hideLoader()
        binding.emptyLayout.emptyContainer.visibility = View.GONE
        binding.moviesList.visibility = View.VISIBLE
        moviesListAdapter.setItems(movies)
    }

    private fun handleErrorResponse() {
        hideLoader()
        binding.moviesList.visibility = View.GONE
        binding.emptyLayout.emptyContainer.visibility = View.VISIBLE
    }
}