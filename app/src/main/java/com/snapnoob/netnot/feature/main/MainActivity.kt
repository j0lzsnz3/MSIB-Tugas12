package com.snapnoob.netnot.feature.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.snapnoob.netnot.ContentGenerator
import com.snapnoob.netnot.R
import com.snapnoob.netnot.databinding.ActivityMainBinding
import com.snapnoob.netnot.feature.categorydetail.CategoryDetailActivity
import com.snapnoob.netnot.feature.favourite.MyMovieListAdapter
import com.snapnoob.netnot.feature.moviedetail.MovieDetailActivity
import com.snapnoob.netnot.network.model.Movies
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var view: View

    private lateinit var browseAdapter: BrowseAdapter

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        initView()

        loadBrowseContent()
    }

    private fun initView() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    if (it.position == 0) {
                        loadBrowseContent()
                    } else {
                        loadMyListMovie()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setupRecyclerViewBrowse() {
        binding.rvHome.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            browseAdapter = BrowseAdapter(
                this@MainActivity::openCategoryDetailActivity,
                this@MainActivity::openMovieDetailActivity
            )
            adapter = browseAdapter
        }
    }

    private fun handlePresenterEvent(event: MainPresenterEvent) {
        synchronized(this) {
            when (event) {
                is MainPresenterEvent.ShowPopularMovies -> {
                    browseAdapter.setData(createContentView(event.movies, ContentCategory.POPULAR))
                }
                is MainPresenterEvent.ShowTopRatedMovies -> {
                    browseAdapter.setData(
                        createContentView(
                            event.movies,
                            ContentCategory.TOP_RATED
                        )
                    )
                }
                is MainPresenterEvent.ShowError -> {
                    Snackbar.make(view, event.error, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun createContentView(
        movies: Movies,
        category: ContentCategory
    ): ContentView {
        val movieViews = mutableListOf<MovieView>()
        // set limited because it's just a overview
        if (movies.moviesResults.size >= 5) {
            movies.moviesResults.take(5).forEach {
                movieViews.add(
                    MovieView(
                        movieId = it.id,
                        posterPath = it.posterPath
                    )
                )
            }
        }
        val contentTitle = if (category.name == ContentCategory.POPULAR.name) {
            getString(R.string.popular_movies)
        } else {
            getString(R.string.top_rated)
        }

        return ContentView(
            title = contentTitle,
            contentCategory = category,
            posterPaths = movieViews
        )
    }

    private fun loadBrowseContent() {
        setupRecyclerViewBrowse()
        presenter.getPopularMovies(this::handlePresenterEvent)
        presenter.getTopRatedMovies(this::handlePresenterEvent)
    }

    private fun loadMyListMovie() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            val myMovieListAdapter = MyMovieListAdapter()
            adapter = myMovieListAdapter
            myMovieListAdapter.setData(ContentGenerator.createMyMovieListViews())
        }
    }

    private fun openCategoryDetailActivity(isFromTrending: Boolean) {
        val intent = Intent(this, CategoryDetailActivity::class.java)
        intent.putExtra(CategoryDetailActivity.IS_FROM_POPULAR, isFromTrending)
        startActivity(intent)
    }

    private fun openMovieDetailActivity(movieId: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_ID, movieId)
        startActivity(intent)
    }
}