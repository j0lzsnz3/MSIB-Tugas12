package com.snapnoob.netnot.feature.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snapnoob.netnot.databinding.ViewBrowseBinding

class BrowseAdapter(
    private val setOnClickAllListener: (Boolean) -> Unit,
    private val setOnImageClickListener: (Int) -> Unit
) : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {
    private var contentViews: MutableList<ContentView> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(content: ContentView) {
        contentViews.add(content)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewBrowseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding, setOnClickAllListener, setOnImageClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contentViews[position])
    }

    override fun getItemCount(): Int = contentViews.size

    inner class ViewHolder(
        private val binding: ViewBrowseBinding,
        private val isFromPopularViewListener: (Boolean) -> Unit,
        private val setOnImageClickListener: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(contentView: ContentView) {
            val context = itemView.context

            binding.tvContentTitle.text = contentView.title

            val isPopularView = contentView.contentCategory.name == ContentCategory.POPULAR.name
            binding.imageView.setOnClickListener { isFromPopularViewListener.invoke(isPopularView) }
            binding.textView2.setOnClickListener { isFromPopularViewListener.invoke(isPopularView) }

            if (contentView.subTitle != null) {
                binding.tvContentSubtitle.text = contentView.subTitle
            } else {
                binding.tvContentSubtitle.visibility = View.GONE
            }

            binding.rvContent.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ContentAdapter(contentView.posterPaths) { movieId ->
                    setOnImageClickListener.invoke(movieId)
                }
            }
        }
    }

}