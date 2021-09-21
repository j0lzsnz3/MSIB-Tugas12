package com.snapnoob.netnot

import com.snapnoob.netnot.feature.favourite.MyMovieListView

object ContentGenerator {

    fun createMyMovieListViews(): List<MyMovieListView> {
        return listOf(
            MyMovieListView(
                title = "Sherini",
                year = "2017",
                season = "Season 2",
                imageUrl = "https://resize.indiatvnews.com/en/resize/newbucket/715_-/2021/06/vidya-balan-sherni-1622612164.jpg"
            ),
            MyMovieListView(
                title = "Ready or Not",
                year = "2000",
                season = "Season 1",
                imageUrl = "https://www.startattle.com/wp-content/uploads/2019/07/ready-or-not-2019-movie-samara-weaving-adam-brody-500x300.jpg"
            ),
            MyMovieListView(
                title = "Mortal Kombat",
                year = "2020",
                season = "Season 4",
                imageUrl = "https://newretrowave.com/wp-content/uploads/2021/04/mortal-kombat.jpg"
            )
        )
    }
}