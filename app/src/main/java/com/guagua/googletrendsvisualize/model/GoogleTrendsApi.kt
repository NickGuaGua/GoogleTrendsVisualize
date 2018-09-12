package com.guagua.googletrendsvisualize.model

import retrofit2.Call
import retrofit2.http.*

interface GoogleTrendsApi{

    @GET("/trends/hottrends/visualize/internal/_static/b204632211-dev-ronili.411146578513760570/locale/en/locale.json")
    fun getAllRegions(): Call<HashMap<String, String>>

    @GET("/trends/hottrends/visualize/internal/data")
    fun getAllTrends(): Call<HashMap<String, Array<String>>>

}