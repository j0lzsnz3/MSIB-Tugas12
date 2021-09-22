package com.snapnoob.netnot.di

import com.snapnoob.netnot.data.AppDatabase
import com.snapnoob.netnot.data.dao.FavouriteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun provideFavouriteMovieDao(appDatabase: AppDatabase): FavouriteMovieDao {
        return appDatabase.favouriteMovieDao()
    }
}