package com.guagua.googletrendsvisualize.model

import javax.inject.Inject

class GoogleTrendsRepository: GoogleTrendsDataSource{

    var googleTrendsRemoteDataSource: GoogleTrendsDataSource

    var trends: HashMap<String, Array<String>> = hashMapOf()
    var regions: HashMap<String, String> = hashMapOf()

    @Inject
    constructor(googleTrendsRemoteDataSource: GoogleTrendsDataSource){
        this.googleTrendsRemoteDataSource = googleTrendsRemoteDataSource
    }

    companion object {
        var INSTANCE: GoogleTrendsRepository? = null
        fun getInstance(googleTrendsRemoteDataSource: GoogleTrendsDataSource): GoogleTrendsRepository{
            if (INSTANCE == null) INSTANCE = GoogleTrendsRepository(googleTrendsRemoteDataSource)
            return INSTANCE as GoogleTrendsRepository
        }
    }

    override fun getAllTrends(callback: GoogleTrendsDataSource.GetAllTrendsCallback) {
        googleTrendsRemoteDataSource.getAllTrends(object : GoogleTrendsDataSource.GetAllTrendsCallback{
            override fun onDataReturn(data: HashMap<String, Array<String>>) {
                trends = data
                callback.onDataReturn(data)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun getAllRegions(callback: GoogleTrendsDataSource.GetAllRegionCallback) {
        googleTrendsRemoteDataSource.getAllRegions(object : GoogleTrendsDataSource.GetAllRegionCallback{
            override fun onDataReturn(data: HashMap<String, String>) {
                regions = data
                callback.onDataReturn(data)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

}