package com.snapnoob.netnot.feature.detail

import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.MovieDetail
import com.snapnoob.netnot.repository.MoviesServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface GetMovieDetailUseCase {
    fun getMovieDetail(movieId: Long): Flow<ResultWrapper<MovieDetail>>
}

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val repository: MoviesServiceRepository,
    private val coroutineContext: CoroutineContext
): GetMovieDetailUseCase {
    override fun getMovieDetail(movieId: Long): Flow<ResultWrapper<MovieDetail>> {
        return flow { emit(repository.getMovieDetail(movieId)) }
            .flowOn(coroutineContext)
    }
}