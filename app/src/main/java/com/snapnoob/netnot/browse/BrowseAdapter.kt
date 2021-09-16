package com.snapnoob.netnot.browse

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snapnoob.netnot.R

class BrowseAdapter : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {
    private var contentViews: List<ContentView> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(contents: List<ContentView>) {
        this.contentViews = contents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_browse, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contentViews[position])
    }

    override fun getItemCount(): Int = contentViews.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(contentView: ContentView) {
            val context = itemView.context
            val title = itemView.findViewById<TextView>(R.id.tvContentTitle)
            val subTitle = itemView.findViewById<TextView>(R.id.tvContentSubtitle)
            val rvContent = itemView.findViewById<RecyclerView>(R.id.rvContent)

            title.text = contentView.title

            if (contentView.subTitle != null) {
                subTitle.text = contentView.subTitle
            } else {
                subTitle.visibility = View.GONE
            }

            rvContent.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ContentAdapter(contentView.imageUrls)
            }
        }
    }

}