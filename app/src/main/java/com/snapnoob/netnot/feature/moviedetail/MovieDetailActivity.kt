package com.snapnoob.netnot.feature.moviedetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.snapnoob.netnot.AppConstant
import com.snapnoob.netnot.databinding.ActivityMovieDetailBinding
import com.snapnoob.netnot.network.model.MovieDetail
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var view: View

    @Inject
    lateinit var presenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        if (intent.hasExtra(MOVIE_ID)) {
            val movieId = intent.getIntExtra(MOVIE_ID, 0)
            presenter.loadMovieDetail(movieId, this::handlePresenterEvent)
        }

        binding.toolBar.setNavigationOnClickListener { finish() }
    }

    private fun handlePresenterEvent(event: MovieDetailPresenterEvent) {
        when (event) {
            is MovieDetailPresenterEvent.ShowMovieDetail -> displayData(event.movieDetail)
            is MovieDetailPresenterEvent.ShowError       -> {
                Snackbar.make(view, event.error, Snackbar.LENGTH_LONG).show()
            }
        }
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