package com.snapnoob.netnot.di

import com.snapnoob.netnot.feature.moviedetail.GetMovieDetailUseCase
import com.snapnoob.netnot.feature.moviedetail.GetMovieDetailUseCaseImpl
import com.snapnoob.netnot.feature.favourite.GetAllFavouriteMovieUseCase
import com.snapnoob.netnot.feature.favourite.GetAllFavouriteMovieUseCaseImpl
import com.snapnoob.netnot.feature.main.GetPopularMoviesUseCase
import com.snapnoob.netnot.feature.main.GetPopularMoviesUseCaseImpl
import com.snapnoob.netnot.feature.main.GetTopRatedMoviesUseCase
import com.snapnoob.netnot.feature.main.GetTopRatedMoviesUseCaseImpl
import com.snapnoob.netnot.repository.FavouriteMovieRepository
import com.snapnoob.netnot.repository.MoviesServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetPopularMoviesUseCase(repository: MoviesServiceRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(repository, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideGetTopRatedMoviesUseCase(repository: MoviesServiceRepository): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCaseImpl(repository, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideGetMovieDetailUseCase(repository: MoviesServiceRepository): GetMovieDetailUseCase {
        return GetMovieDetailUseCaseImpl(repository, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideGetAllFavouriteMovieUseCase(repository: FavouriteMovieRepository): GetAllFavouriteMovieUseCase {
        return GetAllFavouriteMovieUseCaseImpl(repository, Dispatchers.IO)
    }
}