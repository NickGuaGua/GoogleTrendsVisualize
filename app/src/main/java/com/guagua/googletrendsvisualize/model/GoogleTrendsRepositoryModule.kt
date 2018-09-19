package com.guagua.googletrendsvisualize.model

import com.guagua.googletrendsvisualize.API
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
class GoogleTrendsRepositoryModule {

    @Provides
    fun provideGoogleTrendsRepository(googleTrendsDataSource: GoogleTrendsDataSource): GoogleTrendsRepository = GoogleTrendsRepository.getInstance(googleTrendsDataSource)

    @Provides
    fun provideGoogleTrendsDataSource(googleTrendsApiService: GoogleTrendsApiService): GoogleTrendsDataSource = GoogleTrendsRemoteDataSource.getInstance(googleTrendsApiService)

    @Provides
    fun provideGoogleTrendsApiService(@Named("GoogleTrendsApi") googleTrendsApi: GoogleTrendsApi): GoogleTrendsApiService = GoogleTrendsApiService.getInstance(googleTrendsApi)

    @Named("GoogleTrendsApi")
    @Provides
    fun provideGoogleTrendsApi(okHttpClient: OkHttpClient) = API(GoogleTrendsApi::class.java, "https://trends.google.com", okHttpClient).provideAPI()

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().addNetworkInterceptor(LogInterceptor()).build()

}
