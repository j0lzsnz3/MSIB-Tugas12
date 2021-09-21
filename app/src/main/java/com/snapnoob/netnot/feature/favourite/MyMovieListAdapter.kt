package com.snapnoob.netnot.feature.favourite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snapnoob.netnot.databinding.ViewMyListBinding

class MyMovieListAdapter : RecyclerView.Adapter<MyMovieListAdapter.ViewHolder>() {
    private var myMovieList: List<MyMovieListView> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<MyMovieListView>) {
        this.myMovieList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewMyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myMovieList[position])
    }

    override fun getItemCount(): Int = myMovieList.size

    inner class ViewHolder(
        private val binding: ViewMyListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myMovieListView: MyMovieListView) {
            Glide.with(binding.root)
                .load(myMovieListView.imageUrl)
                .centerCrop()
                .into(binding.imgMovie)

            binding.tvTitle.text = myMovieListView.title
            binding.tvYear.text = myMovieListView.year
            binding.tvSeason.text = myMovieListView.season
        }
    }

}