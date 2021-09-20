package com.snapnoob.netnot.browse

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snapnoob.netnot.R
import com.snapnoob.netnot.databinding.ViewBrowseBinding

class BrowseAdapter(
    private val setOnClickAllListener: (Boolean) -> Unit
) : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {
    private var contentViews: List<ContentView> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(contents: List<ContentView>) {
        this.contentViews = contents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ViewBrowseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding, setOnClickAllListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contentViews[position])
    }

    override fun getItemCount(): Int = contentViews.size

    inner class ViewHolder(
        private val binding: ViewBrowseBinding,
        private val isFromTrendingListener: (Boolean) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(contentView: ContentView) {
            val context = itemView.context

            binding.tvContentTitle.text = contentView.title

            val isTrendingView = contentView.title.contains("Trending", false)
            binding.imageView.setOnClickListener { isFromTrendingListener.invoke(isTrendingView) }
            binding.textView2.setOnClickListener { isFromTrendingListener.invoke(isTrendingView) }

            if (contentView.subTitle != null) {
                binding.tvContentSubtitle.text = contentView.subTitle
            } else {
                binding.tvContentSubtitle.visibility = View.GONE
            }

            binding.rvContent.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ContentAdapter(contentView.imageUrls)
            }
        }
    }

}