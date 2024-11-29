package com.example.acoustic.di

import android.content.Context
import android.media.MediaPlayer
import com.example.acoustic.common.Constants
import com.example.acoustic.data.SpotifyRepository
import com.example.acoustic.data.SpotifyRepositoryImpl
import com.example.acoustic.data.remote.SpotifyAPi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi():SpotifyAPi{
        return Retrofit.Builder()
            .baseUrl(Constants.SPOTIFY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyAPi::class.java)
    }

    @Provides
    @Singleton
    fun provideSpotifyRepository(api:SpotifyAPi):SpotifyRepository{
        return SpotifyRepositoryImpl(api)
    }
}