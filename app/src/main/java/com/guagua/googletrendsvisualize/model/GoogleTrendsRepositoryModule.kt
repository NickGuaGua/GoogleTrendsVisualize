package com.guagua.googletrendsvisualize.model

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GoogleTrendsRepositoryModule {

    @Provides
    fun provideGoogleTrendsRepository(googleTrendsDataSource: GoogleTrendsDataSource): GoogleTrendsRepository = GoogleTrendsRepository.getInstance(googleTrendsDataSource)

    @Provides
    fun provideGoogleTrendsDataSource(googleTrendsApiService: GoogleTrendsApiService): GoogleTrendsDataSource = GoogleTrendsRemoteDataSource.getInstance(googleTrendsApiService)

    @Provides
    fun provideGoogleTrendsApiService(googleTrendsApi: GoogleTrendsApi): GoogleTrendsApiService = GoogleTrendsApiService.getInstance(googleTrendsApi)

    @Provides
    fun provideGoogleTrendsApi(okHttpClient: OkHttpClient): GoogleTrendsApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://trends.google.com")
            .client(okHttpClient)
            .build().create(GoogleTrendsApi::class.java)

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().addNetworkInterceptor(LogInterceptor()).build()

}
