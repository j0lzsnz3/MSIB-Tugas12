package com.snapnoob.netnot.feature.detail

import com.snapnoob.netnot.data.entity.FavouriteMovie
import com.snapnoob.netnot.repository.FavouriteMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface InsertFavouriteMovieUseCase {
    fun insertFavouriteMovie(favouriteMovie: FavouriteMovie): Flow<Long>
}

class InsertFavouriteMovieUseCaseImpl @Inject constructor(
    private val repository: FavouriteMovieRepository,
    private val coroutineContext: CoroutineContext
): InsertFavouriteMovieUseCase {
    override fun insertFavouriteMovie(favouriteMovie: FavouriteMovie): Flow<Long> =
        repository.insert(favouriteMovie).flowOn(coroutineContext)
}