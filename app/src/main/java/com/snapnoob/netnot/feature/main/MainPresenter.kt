package com.snapnoob.netnot.feature.main

import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.Movies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MainPresenter {
    fun getPopularMovies(result: (MainPresenterEvent) -> Unit)
    fun getTopRatedMovies(result: (MainPresenterEvent) -> Unit)
}

class MainPresenterImpl @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : MainPresenter {
    override fun getPopularMovies(result: (MainPresenterEvent) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            fetchPopularMovies().collect { response ->
                when (response) {
                    is ResultWrapper.Success      -> {
                        result.invoke(MainPresenterEvent.ShowPopularMovies(response.value))
                    }
                    is ResultWrapper.Failed       -> {
                        result.invoke(MainPresenterEvent.ShowError(response.message ?: ""))
                    }
                    is ResultWrapper.NetworkError -> {
                        result.invoke(MainPresenterEvent.ShowError("Network Error"))
                    }
                }
            }
        }
    }

    override fun getTopRatedMovies(result: (MainPresenterEvent) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            fetchTopRatedMovies().collect { response ->
                when (response) {
                    is ResultWrapper.Success      -> {
                        result.invoke(MainPresenterEvent.ShowTopRatedMovies(response.value))
                    }
                    is ResultWrapper.Failed       -> {
                        result.invoke(MainPresenterEvent.ShowError(response.message ?: ""))
                    }
                    is ResultWrapper.NetworkError -> {
                        result.invoke(MainPresenterEvent.ShowError("Network Error"))
                    }
                }
            }
        }
    }

    private suspend fun fetchPopularMovies() = withContext(Dispatchers.IO) {
        getPopularMoviesUseCase.getPopularMovies()
    }

    private suspend fun fetchTopRatedMovies() = withContext(Dispatchers.IO) {
        getTopRatedMoviesUseCase.getTopRatedMovies()
    }
}

sealed class MainPresenterEvent {
    class ShowPopularMovies(val movies: Movies) : MainPresenterEvent()
    class ShowTopRatedMovies(val movies: Movies) : MainPresenterEvent()
    class ShowError(val error: String) : MainPresenterEvent()
}