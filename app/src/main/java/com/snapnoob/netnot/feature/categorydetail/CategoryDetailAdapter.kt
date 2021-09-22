package com.snapnoob.netnot.feature.categorydetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snapnoob.netnot.AppConstant
import com.snapnoob.netnot.databinding.ViewCategoryDetailBinding

class CategoryDetailAdapter : RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {
    private var categoryDetailViews: List<CategoryDetailView> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(views: List<CategoryDetailView>) {
        this.categoryDetailViews = views
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewCategoryDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryDetailViews[position])
    }

    override fun getItemCount(): Int = categoryDetailViews.size

    inner class ViewHolder(
        private val binding: ViewCategoryDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(view: CategoryDetailView) {
            Glide.with(binding.root)
                .load(AppConstant.IMAGE_URL_500.plus(view.imageUrl))
                .fitCenter()
                .into(binding.imgMovie)

            binding.tvMovieTitle.text = view.title
            binding.tvMovieYear.text = view.year
        }
    }
}