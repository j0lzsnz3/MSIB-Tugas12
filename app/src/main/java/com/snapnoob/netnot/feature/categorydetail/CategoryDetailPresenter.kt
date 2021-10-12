package com.snapnoob.netnot.feature.categorydetail

import com.snapnoob.netnot.feature.main.GetPopularMoviesUseCase
import com.snapnoob.netnot.feature.main.GetTopRatedMoviesUseCase
import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.Movies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CategoryDetailPresenter {
    fun getPopularMovies(result: (CategoryDetailPresenterEvent) -> Unit)
    fun getTopRatedMovies(result: (CategoryDetailPresenterEvent) -> Unit)
}

class CategoryDetailPresenterImpl @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : CategoryDetailPresenter {
    override fun getPopularMovies(result: (CategoryDetailPresenterEvent) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            fetchPopularMovies().collect { response ->
                when (response) {
                    is ResultWrapper.Success      -> {
                        result.invoke(CategoryDetailPresenterEvent.ShowMovies(response.value))
                    }
                    is ResultWrapper.Failed       -> {
                        result.invoke(
                            CategoryDetailPresenterEvent.ShowError(
                                response.message ?: ""
                            )
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        result.invoke(CategoryDetailPresenterEvent.ShowError("Network Error"))
                    }
                }
            }
        }
    }

    override fun getTopRatedMovies(result: (CategoryDetailPresenterEvent) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            fetchTopRatedMovies().collect { response ->
                when (response) {
                    is ResultWrapper.Success      -> {
                        result.invoke(CategoryDetailPresenterEvent.ShowMovies(response.value))
                    }
                    is ResultWrapper.Failed       -> {
                        result.invoke(
                            CategoryDetailPresenterEvent.ShowError(
                                response.message ?: ""
                            )
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        result.invoke(CategoryDetailPresenterEvent.ShowError("Network Error"))
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

sealed class CategoryDetailPresenterEvent {
    class ShowMovies(val movies: Movies) : CategoryDetailPresenterEvent()
    class ShowError(val error: String) : CategoryDetailPresenterEvent()
}