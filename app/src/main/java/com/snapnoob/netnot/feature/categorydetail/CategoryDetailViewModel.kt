package com.snapnoob.netnot.feature.categorydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapnoob.netnot.feature.SingleLiveEvent
import com.snapnoob.netnot.feature.main.GetPopularMoviesUseCase
import com.snapnoob.netnot.feature.main.GetTopRatedMoviesUseCase
import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val categoryDetailEvent = SingleLiveEvent<CategoryDetailEvent>()
    val categoryDetailEventLiveData get() = categoryDetailEvent as LiveData<CategoryDetailEvent>

    private val moviesData = SingleLiveEvent<Movies>()
    val moviesLiveData get() = moviesData as LiveData<Movies>

    fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        result.value.let { movies ->
                            moviesData.postValue(movies)
                        }
                    }
                    is ResultWrapper.Failed -> {
                        categoryDetailEvent.postValue(CategoryDetailEvent.ShowError(result.message ?: ""))
                    }
                    is ResultWrapper.NetworkError -> {
                        categoryDetailEvent.postValue(CategoryDetailEvent.ShowError("Network Error"))
                    }
                }
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            getTopRatedMoviesUseCase.getTopRatedMovies().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        result.value.let { movies ->
                            moviesData.postValue(movies)
                        }
                    }
                    is ResultWrapper.Failed -> {
                        categoryDetailEvent.postValue(CategoryDetailEvent.ShowError(result.message ?: ""))
                    }
                    is ResultWrapper.NetworkError -> {
                        categoryDetailEvent.postValue(CategoryDetailEvent.ShowError("Network Error"))
                    }
                }
            }
        }
    }
}

sealed class CategoryDetailEvent {
    class ShowError(val error: String): CategoryDetailEvent()
}