package com.snapnoob.netnot

import com.snapnoob.netnot.browse.ContentView
import com.snapnoob.netnot.category.CategoryDetailView
import com.snapnoob.netnot.mymovielist.MyMovieListView

object ContentGenerator {

    fun createBrowseContentViews(): List<ContentView> {
        return listOf(
            ContentView(
                title = "Trending Now",
                subTitle = "Recomended for you",
                imageUrls = createImageUrlsTrending()
            ),
            ContentView(
                title = "Continue Watching for Imam",
                imageUrls = createImageUrlsContinueWatching()
            )
        )
    }

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

    fun createCategoryTrendingViews(): List<CategoryDetailView> {
        return listOf(
            CategoryDetailView(
                "Run",
                "https://cdn.tatlerasia.com/asiatatler/i/hk/2021/06/01113317-most-watched-netflix-original-movies-2021-extraction_cover_1280x720.jpeg",
                "2019"
            ),
            CategoryDetailView(
                "Cash Truck",
                "https://media.matamata.com/thumbs/2021/07/20/16358-rekomendasi-drama-jepang-di-netflix-asianwikisignal-japanese-drama/o-img-16358-rekomendasi-drama-jepang-di-netflix-asianwikisignal-japanese-drama.jpg",
                "2009"
            ),
            CategoryDetailView(
                "Invasao Zombie",
                "https://cdn.idntimes.com/content-images/post/20171128/ga-2-fd977577898b217a528e679672ce8baa-5780102a87b8f74b86dc4f67d5c5aac0_600x400.jpg",
                "2016"
            ),
            CategoryDetailView(
                "Bad Boy",
                "https://cultura.id/wp-content/uploads/2019/12/triple-frontier-1024x576.jpg",
                "2017"
            )
        )
    }

    fun createCategoryContinueWatching(): List<CategoryDetailView> {
        return listOf(
            CategoryDetailView(
                "Foreigner",
                "http://media-s3-us-east-1.ceros.com/editorial-content/images/2020/02/20/70e370984ab7f2c4677f27960b9480bf/1200x630-2.jpg",
                "2015"
            ),
            CategoryDetailView(
                "The Raid 2",
                "https://blue.kumparan.com/image/upload/fl_progressive,fl_lossy,c_fill,q_auto:best,w_640/v1625563043/nhflpt7jh8zqnu7loccn.png",
                "2018"
            ),
            CategoryDetailView(
                "Shutter",
                "https://digstraksi.com/wp-content/uploads/2020/06/TEL_160277_T_00_big.jpg",
                "2017"
            ),
            CategoryDetailView(
                "Parasite",
                "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_FMjpg_UX1000_.jpg",
                "2019"
            )
        )
    }

}