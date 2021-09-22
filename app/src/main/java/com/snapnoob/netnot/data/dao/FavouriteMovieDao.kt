package com.snapnoob.netnot.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snapnoob.netnot.data.entity.FavouriteMovie

@Dao
interface FavouriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favouriteMovie: FavouriteMovie): Long

    @Query("SELECT * FROM favourite_movie")
    fun getAll(): List<FavouriteMovie>
}