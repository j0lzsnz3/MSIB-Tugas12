package com.snapnoob.netnot.di

import android.content.Context
import androidx.room.Room
import com.snapnoob.netnot.data.AppDatabase
import com.snapnoob.netnot.data.AppPreference
import com.snapnoob.netnot.data.AppPreferenceImpl
import com.snapnoob.netnot.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideAppPreference(@ApplicationContext context: Context): AppPreference {
        return AppPreferenceImpl(context)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(@ApplicationContext context: Context): RetrofitService {
        return RetrofitService(context)
    }
}