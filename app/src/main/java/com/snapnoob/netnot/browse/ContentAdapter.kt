package com.snapnoob.netnot.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snapnoob.netnot.R
import com.snapnoob.netnot.databinding.ViewBrowseContentBinding

class ContentAdapter(
    private val imageUrls: List<String>
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewBrowseContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size

    inner class ViewHolder(
        private val binding: ViewBrowseContentBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            Glide.with(binding.root)
                .load(imageUrl)
                .centerCrop()
                .into(binding.imgMovie)
        }
    }

}