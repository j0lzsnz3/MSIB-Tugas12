package com.snapnoob.netnot.di

import com.snapnoob.netnot.feature.categorydetail.CategoryDetailPresenter
import com.snapnoob.netnot.feature.categorydetail.CategoryDetailPresenterImpl
import com.snapnoob.netnot.feature.main.GetPopularMoviesUseCase
import com.snapnoob.netnot.feature.main.GetTopRatedMoviesUseCase
import com.snapnoob.netnot.feature.main.MainPresenter
import com.snapnoob.netnot.feature.main.MainPresenterImpl
import com.snapnoob.netnot.feature.moviedetail.GetMovieDetailUseCase
import com.snapnoob.netnot.feature.moviedetail.MovieDetailPresenter
import com.snapnoob.netnot.feature.moviedetail.MovieDetailPresenterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object PresenterModule {

    @Provides
    @ActivityScoped
    fun provideMovieDetailPresenter(getMovieDetailUseCase: GetMovieDetailUseCase): MovieDetailPresenter {
        return MovieDetailPresenterImpl(getMovieDetailUseCase)
    }

    @Provides
    @ActivityScoped
    fun provideMainPresenter(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
    ): MainPresenter {
        return MainPresenterImpl(getPopularMoviesUseCase, getTopRatedMoviesUseCase)
    }

    @Provides
    @ActivityScoped
    fun provideCategoryDetailPresenter(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
    ): CategoryDetailPresenter {
        return CategoryDetailPresenterImpl(getPopularMoviesUseCase, getTopRatedMoviesUseCase)
    }
}