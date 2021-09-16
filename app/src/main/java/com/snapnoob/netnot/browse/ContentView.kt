package com.snapnoob.netnot.browse

data class ContentView(
    val title: String,
    val subTitle: String? = null,
    val imageUrls: List<String>
)