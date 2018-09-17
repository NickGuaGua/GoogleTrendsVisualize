package com.guagua.googletrendsvisualize.model

import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class GoogleTrendsApiService: GoogleTrendsDataSource {

    private var googleTrendsApi: GoogleTrendsApi

    @Inject
    constructor(googleTrendsApi: GoogleTrendsApi){
        this.googleTrendsApi = googleTrendsApi
    }

    companion object {
        var INSTANCE: GoogleTrendsApiService? = null

        fun getInstance(googleTrendsApi: GoogleTrendsApi):GoogleTrendsApiService{
            if (INSTANCE == null) INSTANCE = GoogleTrendsApiService(googleTrendsApi)
            return INSTANCE as GoogleTrendsApiService
        }
    }

    override fun getAllTrends(callback: GoogleTrendsDataSource.GetAllTrendsCallback) {

        googleTrendsApi.getAllTrends().enqueue(object : retrofit2.Callback<HashMap<String, Array<String>>>{
            override fun onFailure(call: Call<HashMap<String, Array<String>>>?, t: Throwable?) {
                callback.onDataNotAvailable()
            }

            override fun onResponse(call: Call<HashMap<String, Array<String>>>?, response: Response<HashMap<String, Array<String>>>?) {
                if (response?.body() != null)
                    callback.onDataReturn(response.body()!!)
                else
                    callback.onDataNotAvailable()
            }
        })
    }

    override fun getAllRegions(callback: GoogleTrendsDataSource.GetAllRegionCallback) {

        googleTrendsApi.getAllRegions().enqueue(object : retrofit2.Callback<HashMap<String, String>>{
            override fun onFailure(call: Call<HashMap<String, String>>?, t: Throwable?) {
                callback.onDataNotAvailable()
            }

            override fun onResponse(call: Call<HashMap<String, String>>?, response: Response<HashMap<String, String>>?) {
                if (response?.body() != null)
                    callback.onDataReturn(response.body()!!)
                else
                    callback.onDataNotAvailable()
            }
        })
    }

}
