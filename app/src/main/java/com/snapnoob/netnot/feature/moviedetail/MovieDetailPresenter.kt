package com.snapnoob.netnot.feature.moviedetail

import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.MovieDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieDetailPresenter {
    fun loadMovieDetail(movieId: Int, result: (MovieDetailPresenterEvent) -> Unit)
}

class MovieDetailPresenterImpl @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : MovieDetailPresenter {
    override fun loadMovieDetail(movieId: Int, result: (MovieDetailPresenterEvent) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            fetchMovieDetail(movieId).collect { response ->
                when (response) {
                    is ResultWrapper.Success      -> {
                        response.value.let {
                            result.invoke(MovieDetailPresenterEvent.ShowMovieDetail(it))
                        }
                    }
                    is ResultWrapper.Failed       -> {
                        result.invoke(MovieDetailPresenterEvent.ShowError(response.message ?: ""))
                    }
                    is ResultWrapper.NetworkError -> {
                        result.invoke(MovieDetailPresenterEvent.ShowError("Network Error"))
                    }
                }
            }
        }
    }

    private suspend fun fetchMovieDetail(movieId: Int) = withContext(Dispatchers.IO) {
        getMovieDetailUseCase.getMovieDetail(movieId)
    }
}

sealed class MovieDetailPresenterEvent {
    class ShowMovieDetail(val movieDetail: MovieDetail) : MovieDetailPresenterEvent()
    class ShowError(val error: String) : MovieDetailPresenterEvent()
}