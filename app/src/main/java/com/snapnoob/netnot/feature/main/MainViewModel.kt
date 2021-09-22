package com.snapnoob.netnot.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapnoob.netnot.feature.SingleLiveEvent
import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val mainEvent = SingleLiveEvent<MainEvent>()
    val mainEventLiveData get() = mainEvent as LiveData<MainEvent>

    private val popularMovies = SingleLiveEvent<Movies>()
    val popularMoviesLiveData get() = popularMovies as LiveData<Movies>

    private val topRatedMovies = SingleLiveEvent<Movies>()
    val topRatedMoviesLiveData get() = topRatedMovies as LiveData<Movies>

    fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        result.value.let { movies ->
                            popularMovies.postValue(movies)
                        }
                    }
                    is ResultWrapper.Failed -> {
                        mainEvent.postValue(MainEvent.ShowError(result.message ?: ""))
                    }
                    is ResultWrapper.NetworkError -> {
                        mainEvent.postValue(MainEvent.ShowError("Network Error"))
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
                            topRatedMovies.postValue(movies)
                        }
                    }
                    is ResultWrapper.Failed -> {
                        mainEvent.postValue(MainEvent.ShowError(result.message ?: ""))
                    }
                    is ResultWrapper.NetworkError -> {
                        mainEvent.postValue(MainEvent.ShowError("Network Error"))
                    }
                }
            }
        }
    }

}

sealed class MainEvent {
    class ShowError(val error: String): MainEvent()
}