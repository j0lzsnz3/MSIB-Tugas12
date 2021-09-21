package com.snapnoob.netnot.feature.categorydetail

import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.Movies
import com.snapnoob.netnot.repository.MoviesServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface GetTopRatedMoviesUseCase {
    fun getTopRatedMovies(): Flow<ResultWrapper<Movies>>
}

class GetTopRatedMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesServiceRepository,
    private val coroutineContext: CoroutineContext
): GetTopRatedMoviesUseCase {
    override fun getTopRatedMovies(): Flow<ResultWrapper<Movies>> {
        return flow { emit(repository.getTopRatedMovies()) }
            .flowOn(coroutineContext)
    }
}