package com.santiago.mvvm.adapter

import android.app.Activity

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santiago.mvvm.R
import com.santiago.mvvm.data.local.entity.MovieEntity
import com.santiago.mvvm.databinding.ListItemMovieBinding
import com.squareup.picasso.Picasso


class MoviesListAdapter(private val activity: Activity) : RecyclerView.Adapter<MoviesListAdapter.CustomViewHolder>() {
    private var movies: MutableList<MovieEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListAdapter.CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    fun setItems(movies: List<MovieEntity>) {
        val startPosition = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeChanged(startPosition, movies.size)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun getItem(position: Int): MovieEntity {
        return movies[position]
    }

    override fun onBindViewHolder(holder: MoviesListAdapter.CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class CustomViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels

            itemView.layoutParams = RecyclerView.LayoutParams(
                (width * 0.85f).toInt(),
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }

        fun bindTo(movie: MovieEntity) {
            Picasso.get().load(movie.getFormattedPosterPath())
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.image)
        }
    }
}