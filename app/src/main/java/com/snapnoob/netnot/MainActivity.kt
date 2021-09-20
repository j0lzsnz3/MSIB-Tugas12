package com.snapnoob.netnot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.snapnoob.netnot.browse.BrowseAdapter
import com.snapnoob.netnot.category.CategoryDetailActivity
import com.snapnoob.netnot.databinding.ActivityMainBinding
import com.snapnoob.netnot.mymovielist.MyMovieListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var view: View

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

    private fun loadBrowseContent() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            val browseAdapter = BrowseAdapter {
                openCategoryDetailActivity(it)
            }
            adapter = browseAdapter
            browseAdapter.setData(ContentGenerator.createBrowseContentViews())
        }
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
        intent.putExtra(CategoryDetailActivity.IS_FROM_TRENDING, isFromTrending)
        startActivity(intent)
    }
}