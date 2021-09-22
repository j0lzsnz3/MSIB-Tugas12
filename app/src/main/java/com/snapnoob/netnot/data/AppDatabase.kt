package com.snapnoob.netnot.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snapnoob.netnot.data.dao.FavouriteMovieDao
import com.snapnoob.netnot.data.entity.FavouriteMovie

@Database(entities = [FavouriteMovie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouriteMovieDao(): FavouriteMovieDao

    companion object {
        const val DATABASE_NAME = "netnot_app"
    }
}