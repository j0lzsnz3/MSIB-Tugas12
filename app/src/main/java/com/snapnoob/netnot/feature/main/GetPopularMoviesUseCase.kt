package com.snapnoob.netnot.feature.main

import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.Movies
import com.snapnoob.netnot.repository.MoviesServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface GetPopularMoviesUseCase {
    fun getPopularMovies(): Flow<ResultWrapper<Movies>>
}

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesServiceRepository,
    private val coroutineContext: CoroutineContext
): GetPopularMoviesUseCase {
    override fun getPopularMovies(): Flow<ResultWrapper<Movies>> {
        return flow { emit(repository.getPopularMovies()) }
            .flowOn(coroutineContext)
    }
}