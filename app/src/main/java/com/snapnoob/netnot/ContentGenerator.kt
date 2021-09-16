package com.snapnoob.netnot

import com.snapnoob.netnot.browse.ContentView
import com.snapnoob.netnot.mymovielist.MyMovieListView

object ContentGenerator {

    fun createBrowseContentViews(): List<ContentView> {
        return listOf(
            ContentView(title = "Trending Now", subTitle = "Recomended for you", imageUrls = createImageUrlsTrending()),
            ContentView(title = "Continue Watching for Imam", imageUrls = createImageUrlsContinueWatching())
        )
    }

    fun createMyMovieListViews(): List<MyMovieListView> {
        return listOf(
            MyMovieListView(title = "Sherini", year = "2017", season = "Season 2", imageUrl = "https://resize.indiatvnews.com/en/resize/newbucket/715_-/2021/06/vidya-balan-sherni-1622612164.jpg"),
            MyMovieListView(title = "Ready or Not", year = "2000", season = "Season 1", imageUrl = "https://www.startattle.com/wp-content/uploads/2019/07/ready-or-not-2019-movie-samara-weaving-adam-brody-500x300.jpg"),
            MyMovieListView(title = "Mortal Kombat", year = "2020", season = "Season 4", imageUrl = "https://newretrowave.com/wp-content/uploads/2021/04/mortal-kombat.jpg")
        )
    }

    private fun createImageUrlsTrending(): List<String> {
        return listOf(
            "https://m.media-amazon.com/images/M/MV5BZjdmZDlkMzItNTg0OS00MjAxLWE0ZTQtZjdhOWM1MTJkOTc0XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_FMjpg_UX1000_.jpg",
            "https://m.media-amazon.com/images/M/MV5BYTA3MTdiOGMtY2EwNC00OTljLTg1YWItNmNkNDNlOThkOTFmXkEyXkFqcGdeQXVyMTA3MDk2NDg2._V1_.jpg",
            "https://m.media-amazon.com/images/M/MV5BZTI0OTAxZjYtZTVlNi00NDU0LTlkNDAtMTY3YTA0NGUwOWQ1XkEyXkFqcGdeQXVyMTAyOTE2ODg0._V1_.jpg",
            "https://m.media-amazon.com/images/M/MV5BYmZiNDQ0OGEtYTM2ZC00YjI2LTk4MTAtOTdhNGUxZTk4NjEwXkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTU3MDUxMDI0MV5BMl5BanBnXkFtZTgwMzk3OTg3MDI@._V1_FMjpg_UX1000_.jpg"
        )
    }

    private fun createImageUrlsContinueWatching(): List<String> {
        return listOf(
            "https://m.media-amazon.com/images/M/MV5BZWMwZGQzOWItYjM5Ny00MGQ5LTkzYjgtOGJmNjE3ODg4OTQxXkEyXkFqcGdeQXVyMjQwMjk0NjI@._V1_FMjpg_UX1000_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTg5MTE2NjA4OV5BMl5BanBnXkFtZTgwMTUyMjczMTE@._V1_FMjpg_UX1000_.jpg",
            "https://m.media-amazon.com/images/M/MV5BMTU1NDAwMDMyNl5BMl5BanBnXkFtZTcwNzU3NTg1MQ@@._V1_FMjpg_UX1000_.jpg",
            "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_FMjpg_UX1000_.jpg",
            "https://m.media-amazon.com/images/M/MV5BOWE0MGRmYmEtMGJmOC00OGE3LTlmZGYtNjk4MzZmNmEzZmI0XkEyXkFqcGdeQXVyNzg5MzIyOA@@._V1_FMjpg_UX1000_.jpg"
        )
    }
}