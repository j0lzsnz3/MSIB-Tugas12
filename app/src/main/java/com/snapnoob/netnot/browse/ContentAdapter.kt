package com.snapnoob.netnot.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snapnoob.netnot.R

class ContentAdapter(
    private val imageUrls: List<String>
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_browse_content, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(imageUrl: String) {
            val imageMovie = itemView.findViewById<ImageView>(R.id.imgMovie)

            Glide.with(itemView)
                .load(imageUrl)
                .centerCrop()
                .into(imageMovie)
        }
    }

}