package com.guagua.googletrendsvisualize

import com.guagua.googletrendsvisualize.model.GoogleTrendsApiService
import com.guagua.googletrendsvisualize.model.GoogleTrendsRemoteDataSource
import com.guagua.googletrendsvisualize.model.GoogleTrendsRepository

object Injection{
    fun provideGoogleTrendsRepository(): GoogleTrendsRepository = GoogleTrendsRepository.getInstance(GoogleTrendsRemoteDataSource.getInstance(GoogleTrendsApiService.getInstance()))
}