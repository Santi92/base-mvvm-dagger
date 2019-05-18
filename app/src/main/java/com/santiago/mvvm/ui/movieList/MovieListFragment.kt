package com.santiago.mvvm.ui.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.santiago.mvvm.R
import com.santiago.mvvm.custom.PagerSnapHelper
import com.santiago.mvvm.custom.RecyclerSnapItemListener
import com.santiago.mvvm.data.local.entity.MovieEntity
import com.santiago.mvvm.databinding.FragmentMovieListBinding
import com.santiago.mvvm.utils.FragmentNavigatorExtras
import com.santiago.mvvm.utils.autoCleared

import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieListFragment: Fragment(){

    companion object {
        var binding: FragmentMovieListBinding? =  null
    }
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var moviesListViewModel: MovieListViewModel



    private  var moviesListAdapter  by autoCleared<MoviesListAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (binding == null){
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
            sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.move)
        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ( binding!!.moviesList.adapter == null){
            initialiseView()
            initialiseViewModel()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun initialiseView() {

        moviesListAdapter = MoviesListAdapter(requireActivity()){ movie, image ->
            val extras = FragmentNavigatorExtras(
                image to movie.id.toString()
            )
            NavHostFragment
                .findNavController(this)
                .navigate(MovieListFragmentDirections.showMovieDetail(movie.id),extras)
        }

        binding!!.moviesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding!!.moviesList.adapter = moviesListAdapter

        val startSnapHelper = PagerSnapHelper(
            object : RecyclerSnapItemListener {

                override fun onItemSnap(position: Int) {
                    val movie = moviesListAdapter.getItem(position)
                    (requireActivity() as MainActivity).updateBackground(movie.getFormattedPosterPath())
                }
            }
        )

        startSnapHelper.attachToRecyclerView(binding!!.moviesList)
        postponeEnterTransition()
        binding!!.moviesList.getViewTreeObserver()
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        startPostponedEnterTransition()
    }



    private fun initialiseViewModel() {
        moviesListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        moviesListViewModel.reload(true)
        moviesListViewModel.listMovie.observe(this, Observer { resource ->
            if (resource!!.isLoading) {
                displayLoader()

            } else if (resource.data != null && !resource.data.isEmpty()) {
                updateMoviesList(resource.data)

            } else
                handleErrorResponse()
        })
    }


    private fun displayLoader() {
        binding!!.moviesList.visibility = View.GONE
        binding!!.loaderLayout.rootView.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding!!.moviesList.visibility = View.VISIBLE
        binding!!.loaderLayout.rootView.visibility = View.GONE
    }

    private fun updateMoviesList(movies: List<MovieEntity>) {
        hideLoader()
        binding!!.emptyLayout.emptyContainer.visibility = View.GONE
        binding!!.moviesList.visibility = View.VISIBLE
        moviesListAdapter.setItems(movies)
    }

    private fun handleErrorResponse() {
        hideLoader()
        binding!!.moviesList.visibility = View.GONE
        binding!!.emptyLayout.emptyContainer.visibility = View.VISIBLE
    }

}