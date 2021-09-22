package com.snapnoob.netnot.feature.main

data class ContentView(
    val title: String,
    val contentCategory: ContentCategory,
    val subTitle: String? = null,
    val posterPaths: List<MovieView>
)

data class MovieView(
    val movieId: Int,
    val posterPath: String
)

enum class ContentCategory {
    POPULAR, TOP_RATED
}