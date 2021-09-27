package com.snapnoob.netnot.feature.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.snapnoob.netnot.AppConstant
import com.snapnoob.netnot.R
import com.snapnoob.netnot.databinding.ActivityMovieDetailBinding
import com.snapnoob.netnot.network.model.MovieDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var view: View

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        observeViewModel()

        if (intent.hasExtra(MOVIE_ID)) {
            val movieId = intent.getIntExtra(MOVIE_ID, 0)
            viewModel.loadMovieDetail(movieId)
        }

        binding.toolBar.setNavigationOnClickListener { finish() }
    }

    private fun observeViewModel() {
        viewModel.movieDetailEventLiveData.observe(this, { event ->
            when (event) {
                is MovieDetailViewModel.MovieDetailEvent.ShowError -> {
                    Snackbar.make(view, event.error, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        viewModel.movieLiveData.observe(this, this@MovieDetailActivity::displayData)
    }

    private fun displayData(movieDetail: MovieDetail) {

        binding.toolBar.title = movieDetail.title

        Glide.with(view)
            .load(AppConstant.IMAGE_URL_500.plus(movieDetail.backdropPath))
            .centerCrop()
            .into(binding.imgMovieBanner)

        binding.originalLanguage.text = "Language: ".plus(movieDetail.originalLanguage.uppercase())
        binding.tvAdultContent.isVisible = movieDetail.adult
        binding.tvOverView.text = movieDetail.overview
    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }
}