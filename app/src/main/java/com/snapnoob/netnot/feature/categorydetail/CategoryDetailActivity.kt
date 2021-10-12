package com.snapnoob.netnot.feature.categorydetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.snapnoob.netnot.R
import com.snapnoob.netnot.databinding.ActivityCategoryDetailBinding
import com.snapnoob.netnot.feature.moviedetail.MovieDetailActivity
import com.snapnoob.netnot.network.model.Movies
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var view: View

    private lateinit var categoryDetailAdapter: CategoryDetailAdapter

    @Inject
    lateinit var presenter: CategoryDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        initView()

        if (intent.hasExtra(IS_FROM_POPULAR)) {
            val isFromPopular = intent.getBooleanExtra(IS_FROM_POPULAR, false)
            if (isFromPopular) {
                presenter.getPopularMovies(this::handlePresenterEvent)
            } else {
                presenter.getTopRatedMovies(this::handlePresenterEvent)
            }

            binding.toolBar.title = if (isFromPopular) getString(R.string.popular_movies) else getString(R.string.top_rated)
            binding.toolBar.setNavigationOnClickListener { finish() }
        } else {
            finish()
        }
    }

    private fun initView() {
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(
                this@CategoryDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            categoryDetailAdapter = CategoryDetailAdapter { openMovieDetailActivity(it) }
            adapter = categoryDetailAdapter
        }
    }

    private fun handlePresenterEvent(event: CategoryDetailPresenterEvent) {
        when (event) {
            is CategoryDetailPresenterEvent.ShowMovies -> displayMovies(event.movies)
            is CategoryDetailPresenterEvent.ShowError  -> {
                Snackbar.make(view, event.error, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun displayMovies(movies: Movies) {
        val categoryDetailViews = createCategoryDetailViews(movies)
        categoryDetailAdapter.setData(categoryDetailViews)
    }

    private fun createCategoryDetailViews(movies: Movies): List<CategoryDetailView> {
        val categoryDetailViews = mutableListOf<CategoryDetailView>()
        movies.moviesResults.forEach {
            categoryDetailViews.add(
                CategoryDetailView(
                    title = it.title,
                    movieId = it.id,
                    imageUrl = it.posterPath,
                    year = it.releaseDate
                )
            )
        }
        return categoryDetailViews
    }

    private fun openMovieDetailActivity(movieId: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID, movieId)
        startActivity(intent)
    }

    companion object {
        const val IS_FROM_POPULAR = "is_from_popular"
    }
}