package com.guagua.googletrendsvisualize.model

import javax.inject.Inject

class GoogleTrendsRemoteDataSource: GoogleTrendsDataSource{

    var googleTrendsApiService: GoogleTrendsApiService

    @Inject
    constructor(googleTrendsApiService: GoogleTrendsApiService){
        this.googleTrendsApiService = googleTrendsApiService
    }

    companion object {
        var INSTANCE: GoogleTrendsRemoteDataSource? = null

        fun getInstance(googleTrendsApiService: GoogleTrendsApiService): GoogleTrendsRemoteDataSource{
            if (INSTANCE == null) INSTANCE = GoogleTrendsRemoteDataSource(googleTrendsApiService)
            return INSTANCE as GoogleTrendsRemoteDataSource
        }
    }

    override fun getAllTrends(callback: GoogleTrendsDataSource.GetAllTrendsCallback) {
        googleTrendsApiService.getAllTrends(callback)
    }

    override fun getAllRegions(callback: GoogleTrendsDataSource.GetAllRegionCallback) {
        googleTrendsApiService.getAllRegions(callback)
    }
}