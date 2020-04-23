package com.sundevs.detailmoviefeature

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.santiago.mvvm.ui.detail.MovieDetailViewModel


import com.squareup.picasso.Picasso
import com.sundevs.detailmoviefeature.R
import com.sundevs.detailmoviefeature.databinding.FragmentMovieDetailBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private var handler = Handler(Looper.getMainLooper())

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)


        arguments?.let {
            Log.i("TAG", "Argument=$arguments")

            var valor = it.get("movieId")
            Log.i("TAG", "movieId=$valor")

            var id = it.getString("img_url")
            Log.i("TAG", "img_url=$id")

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //binding.image.transitionName = params.movieId.toString()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        //val params = MovieDetailFragmentArgs.fromBundle(arguments!!)
        //postponeEnterTransition()
        //loadMovie(params.movieId)
    }

    private fun loadMovie(movieId: Long){
        viewModel.loadMovie(movieId)
        viewModel.movie.observe(this, Observer {
            it.data?.run {
                binding.txtDescription.text = description
                binding.txtNameMovie.text = this.header
                cardView.visibility = View.VISIBLE
                Picasso.get().load(getFormattedPosterPath())
                    .placeholder(R.drawable.ic_placeholder)
                    .into(binding.image)
                startPostponedEnterTransition()
                handler.postDelayed(1000) {
                    //cardView.visibility = View.VISIBLE
                    startPostponedEnterTransition()
                }

            }
        })

    }

}
