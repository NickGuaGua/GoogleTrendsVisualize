package com.guagua.googletrendsvisualize.di

import com.guagua.googletrendsvisualize.model.GoogleTrendsApiService
import com.guagua.googletrendsvisualize.model.GoogleTrendsDataSource
import com.guagua.googletrendsvisualize.model.GoogleTrendsRemoteDataSource
import com.guagua.googletrendsvisualize.model.GoogleTrendsRepository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideGoogleTrendsRepository(googleTrendsDataSource: GoogleTrendsDataSource): GoogleTrendsRepository = GoogleTrendsRepository.getInstance(googleTrendsDataSource)

    @Provides
    fun provideGoogleTrendsDataSource(googleTrendsApiService: GoogleTrendsApiService): GoogleTrendsDataSource = GoogleTrendsRemoteDataSource.getInstance(googleTrendsApiService)

    @Provides
    fun provideGoogleTrendsApiService(): GoogleTrendsApiService = GoogleTrendsApiService.getInstance()

}