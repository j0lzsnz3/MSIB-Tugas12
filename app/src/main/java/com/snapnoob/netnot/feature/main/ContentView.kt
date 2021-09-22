package com.snapnoob.netnot.feature.main

data class ContentView(
    val title: String,
    val contentCategory: ContentCategory,
    val subTitle: String? = null,
    val posterPaths: List<String>
)

enum class ContentCategory {
    POPULAR, TOP_RATED
}