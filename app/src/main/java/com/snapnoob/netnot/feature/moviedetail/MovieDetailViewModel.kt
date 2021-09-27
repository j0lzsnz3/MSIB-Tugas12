package com.snapnoob.netnot.feature.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapnoob.netnot.feature.SingleLiveEvent
import com.snapnoob.netnot.network.ResultWrapper
import com.snapnoob.netnot.network.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val movieDetailEvent = SingleLiveEvent<MovieDetailEvent>()
    val movieDetailEventLiveData get() = movieDetailEvent as LiveData<MovieDetailEvent>

    private val movieData = SingleLiveEvent<MovieDetail>()
    val movieLiveData get() = movieData as LiveData<MovieDetail>

    fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.getMovieDetail(movieId).collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        result.value.let {
                            movieData.postValue(it)
                        }
                    }
                    is ResultWrapper.Failed -> movieDetailEvent.postValue(MovieDetailEvent.ShowError(result.message ?: ""))
                    is ResultWrapper.NetworkError -> movieDetailEvent.postValue(MovieDetailEvent.ShowError("Network Error"))
                }
            }
        }
    }

    sealed class MovieDetailEvent {
        class ShowError(val error: String) : MovieDetailEvent()
    }
}