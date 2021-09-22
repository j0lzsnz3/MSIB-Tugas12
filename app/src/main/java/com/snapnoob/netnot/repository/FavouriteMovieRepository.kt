package com.snapnoob.netnot.repository

import com.snapnoob.netnot.data.dao.FavouriteMovieDao
import com.snapnoob.netnot.data.entity.FavouriteMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface FavouriteMovieRepository {
    fun insert(favouriteMovie: FavouriteMovie): Flow<Long>
    fun getAll(): Flow<List<FavouriteMovie>>
}

class FavouriteMovieRepositoryImpl @Inject constructor(
    private val movieDao: FavouriteMovieDao,
    private val coroutineContext: CoroutineContext
): FavouriteMovieRepository {
    override fun insert(favouriteMovie: FavouriteMovie): Flow<Long> {
        return flow { emit(movieDao.insert(favouriteMovie)) }
            .flowOn(coroutineContext)
    }

    override fun getAll(): Flow<List<FavouriteMovie>> {
        return flow { emit(movieDao.getAll()) }
            .flowOn(coroutineContext)
    }
}