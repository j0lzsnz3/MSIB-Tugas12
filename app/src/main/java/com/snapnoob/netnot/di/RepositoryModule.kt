package com.snapnoob.netnot.di

import com.snapnoob.netnot.data.dao.FavouriteMovieDao
import com.snapnoob.netnot.network.RetrofitService
import com.snapnoob.netnot.repository.FavouriteMovieRepository
import com.snapnoob.netnot.repository.FavouriteMovieRepositoryImpl
import com.snapnoob.netnot.repository.MovieServiceRepositoryImpl
import com.snapnoob.netnot.repository.MoviesServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFavouriteMovieRepository(movieDao: FavouriteMovieDao): FavouriteMovieRepository {
        return FavouriteMovieRepositoryImpl(movieDao, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideMoviesServiceRepository(retrofitService: RetrofitService): MoviesServiceRepository {
        return MovieServiceRepositoryImpl(retrofitService)
    }
}