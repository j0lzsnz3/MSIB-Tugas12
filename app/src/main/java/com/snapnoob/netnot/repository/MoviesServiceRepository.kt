package com.snapnoob.netnot.repository

import com.snapnoob.netnot.network.*
import com.snapnoob.netnot.network.model.ErrorResponse
import com.snapnoob.netnot.network.model.MovieDetail
import com.snapnoob.netnot.network.model.Movies
import javax.inject.Inject

interface MoviesServiceRepository {
    fun getPopularMovies(): ResultWrapper<Movies>
    fun getTopRatedMovies(): ResultWrapper<Movies>
    fun getMovieDetail(movieId: Int): ResultWrapper<MovieDetail>
}

class MovieServiceRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
): MoviesServiceRepository {
    override fun getPopularMovies(): ResultWrapper<Movies> {
        return try {
            val response = retrofitService.getMoviesService()
                .getPopularMovies(retrofitService.getApiKey())
                .execute()

            if (response.isSuccess()) {
                ResultWrapper.Success(response.body()!!)
            } else {
                val errorResponse = response.parseErrorResponse<ErrorResponse>()
                if (errorResponse != null) {
                    ResultWrapper.Failed(errorResponse.statusCode, errorResponse.statusMessage, response.getUrl())
                } else {
                    ResultWrapper.NetworkError
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            ResultWrapper.NetworkError
        }
    }

    override fun getTopRatedMovies(): ResultWrapper<Movies> {
        return try {
            val response = retrofitService.getMoviesService()
                .getTopRatedMovies(retrofitService.getApiKey())
                .execute()

            if (response.isSuccess()) {
                ResultWrapper.Success(response.body()!!)
            } else {
                val errorResponse = response.parseErrorResponse<ErrorResponse>()
                if (errorResponse != null) {
                    ResultWrapper.Failed(errorResponse.statusCode, errorResponse.statusMessage, response.getUrl())
                } else {
                    ResultWrapper.NetworkError
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            ResultWrapper.NetworkError
        }
    }

    override fun getMovieDetail(movieId: Int): ResultWrapper<MovieDetail> {
        return try {
            val response = retrofitService.getMoviesService()
                .getMovieDetail(movieId, retrofitService.getApiKey())
                .execute()

            if (response.isSuccess()) {
                ResultWrapper.Success(response.body()!!)
            } else {
                val errorResponse = response.parseErrorResponse<ErrorResponse>()
                if (errorResponse != null) {
                    ResultWrapper.Failed(errorResponse.statusCode, errorResponse.statusMessage, response.getUrl())
                } else {
                    ResultWrapper.NetworkError
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            ResultWrapper.NetworkError
        }
    }
}