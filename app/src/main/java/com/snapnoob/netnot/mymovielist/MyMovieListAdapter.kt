package com.snapnoob.netnot.mymovielist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snapnoob.netnot.R

class MyMovieListAdapter : RecyclerView.Adapter<MyMovieListAdapter.ViewHolder>() {
    private var myMovieList: List<MyMovieListView> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<MyMovieListView>) {
        this.myMovieList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_my_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myMovieList[position])
    }

    override fun getItemCount(): Int = myMovieList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(myMovieListView: MyMovieListView) {
            val imageMovie = itemView.findViewById<ImageView>(R.id.imgMovie)
            val title = itemView.findViewById<TextView>(R.id.tvTitle)
            val year = itemView.findViewById<TextView>(R.id.tvYear)
            val season = itemView.findViewById<TextView>(R.id.tvSeason)

            Glide.with(itemView)
                .load(myMovieListView.imageUrl)
                .centerCrop()
                .into(imageMovie)

            title.text = myMovieListView.title
            year.text = myMovieListView.year
            season.text = myMovieListView.season
        }
    }

}