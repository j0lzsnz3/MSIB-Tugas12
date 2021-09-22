package com.snapnoob.netnot.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movie")
data class FavouriteMovie(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,

    @ColumnInfo(name = "movie_id")
    val movieId: String,

    @ColumnInfo(name = "movie_title")
    val movieTitle: String
)