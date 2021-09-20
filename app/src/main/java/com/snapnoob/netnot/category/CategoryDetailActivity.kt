package com.snapnoob.netnot.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.snapnoob.netnot.ContentGenerator
import com.snapnoob.netnot.databinding.ActivityCategoryDetailBinding

class CategoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var view: View

    private lateinit var categoryDetailAdapter: CategoryDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)
        initView()

        if (intent.hasExtra(IS_FROM_TRENDING)) {
            val isFromTrending = intent.getBooleanExtra(IS_FROM_TRENDING, false)
            val views = if (isFromTrending) ContentGenerator.createCategoryTrendingViews()
            else ContentGenerator.createCategoryContinueWatching()

            binding.toolBar.title = if (isFromTrending) "Trending" else "Continue Watching"
            binding.toolBar.setNavigationOnClickListener { finish() }

            setDataToAdapter(views)
        } else {
            finish()
        }
    }

    private fun initView() {
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(this@CategoryDetailActivity, LinearLayoutManager.VERTICAL, false)
            categoryDetailAdapter = CategoryDetailAdapter()
            adapter = categoryDetailAdapter
        }
    }

    private fun setDataToAdapter(views: List<CategoryDetailView>) {
        categoryDetailAdapter.setData(views)
    }

    companion object {
        const val IS_FROM_TRENDING = "is_from_trending"
    }
}