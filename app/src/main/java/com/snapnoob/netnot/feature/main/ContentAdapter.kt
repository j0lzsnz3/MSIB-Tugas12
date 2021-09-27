package com.snapnoob.netnot.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snapnoob.netnot.AppConstant
import com.snapnoob.netnot.databinding.ViewBrowseContentBinding

class ContentAdapter(
    private val movieViews: List<MovieView>,
    private val setOnClickListener: (Int) -> Unit
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewBrowseContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding, setOnClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieViews[position])
    }

    override fun getItemCount(): Int = movieViews.size

    inner class ViewHolder(
        private val binding: ViewBrowseContentBinding,
        private val setOnClickListener: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieView: MovieView) {

            binding.imgMovie.setOnClickListener {
                setOnClickListener.invoke(movieView.movieId)
            }

            Glide.with(binding.root)
                .load(AppConstant.IMAGE_URL_500.plus(movieView.posterPath))
                .centerCrop()
                .into(binding.imgMovie)
        }
    }

}