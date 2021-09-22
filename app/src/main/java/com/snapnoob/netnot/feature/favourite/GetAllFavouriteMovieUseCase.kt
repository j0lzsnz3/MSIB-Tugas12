package com.snapnoob.netnot.feature.favourite

import com.snapnoob.netnot.data.entity.FavouriteMovie
import com.snapnoob.netnot.repository.FavouriteMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface GetAllFavouriteMovieUseCase {
    fun getAllFavouriteMovie(): Flow<List<FavouriteMovie>>
}

class GetAllFavouriteMovieUseCaseImpl @Inject constructor(
    private val repository: FavouriteMovieRepository,
    private val coroutineContext: CoroutineContext
): GetAllFavouriteMovieUseCase {
    override fun getAllFavouriteMovie(): Flow<List<FavouriteMovie>> =
        repository.getAll().flowOn(coroutineContext)
}