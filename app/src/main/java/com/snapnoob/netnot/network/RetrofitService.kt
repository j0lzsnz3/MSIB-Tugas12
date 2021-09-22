package com.snapnoob.netnot.network

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitService @Inject constructor(
    private val context: Context
) {
    private var retrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_V3)
                .client(createOkHttpFlipper())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }

    fun getMoviesService(): MoviesService {
        return getRetrofit().create(MoviesService::class.java)
    }

    // for production, should encrypt or hide this key.
    fun getApiKey(): String = API_KEY

    private fun createOkHttpFlipper(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                FlipperOkhttpInterceptor(
                    AndroidFlipperClient.getInstance(context).getPlugin(
                        NetworkFlipperPlugin.ID
                    )
                )
            )
            .build()
    }


    companion object {
        private const val BASE_URL_V3 = "https://api.themoviedb.org/3/"
        private const val API_KEY = "6349faa9fa6f0df7a1d75347e069f2ab"
    }
}