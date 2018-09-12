package com.guagua.googletrendsvisualize.model

import com.guagua.googletrendsvisualize.BaseCallback

interface GoogleTrendsDataSource{

    interface GetAllTrendsCallback: BaseCallback<HashMap<String, Array<String>>>

    interface GetAllRegionCallback: BaseCallback<HashMap<String, String>>

    fun getAllTrends(callback: GetAllTrendsCallback)

    fun getAllRegions(callback: GetAllRegionCallback)

}