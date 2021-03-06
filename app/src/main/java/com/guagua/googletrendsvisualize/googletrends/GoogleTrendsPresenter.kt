package com.guagua.googletrendsvisualize.googletrends

import com.guagua.googletrendsvisualize.model.GoogleTrendsDataSource
import com.guagua.googletrendsvisualize.model.GoogleTrendsRepository
import java.util.*

class GoogleTrendsPresenter: GoogleTrendsContract.Presenter{

    private var googleTrendsRepository: GoogleTrendsRepository
    private lateinit var view: GoogleTrendsContract.View
    private lateinit var trends: HashMap<String, Array<String>>
    private lateinit var regions: HashMap<String, String>

    private var ROW = 7
    private var COLUMN = 3
    private var REGION_INDEX = 0

    constructor(googleTrendsRepository: GoogleTrendsRepository){
        this.googleTrendsRepository = googleTrendsRepository
    }

    companion object {
        var INSTANCE: GoogleTrendsPresenter? = null
        fun getInstance(googleTrendsRepository: GoogleTrendsRepository): GoogleTrendsPresenter{
            if (INSTANCE == null) INSTANCE = GoogleTrendsPresenter(googleTrendsRepository)
            return INSTANCE as GoogleTrendsPresenter
        }

    }

    override fun loadTrendsAndRegions() {
        googleTrendsRepository.getAllTrends(object : GoogleTrendsDataSource.GetAllTrendsCallback{
            override fun onDataReturn(data: HashMap<String, Array<String>>) {
                trends = data
                googleTrendsRepository.getAllRegions(object : GoogleTrendsDataSource.GetAllRegionCallback{
                    override fun onDataReturn(data: HashMap<String, String>) {
                        regions = data
                        removeUnknownRegions()
                        view.onLoadDataCompleted()
                    }
                    override fun onDataNotAvailable() {}
                })
            }
            override fun onDataNotAvailable() {}
        })
    }

    private fun removeUnknownRegions(){
        val regionsIt = regions.iterator()
        while (regionsIt.hasNext()){
            var isUnknownRegion = true
            val regionKey = regionsIt.next().key

            val trendsIt = trends.iterator()
            while (trendsIt.hasNext()){
                val trendKey = trendsIt.next().key
                if (regionKey == trendKey){
                    isUnknownRegion = false
                    break
                }
            }
            if (isUnknownRegion) regionsIt.remove()
        }
    }

    override fun getTrendsInRegion(index: Int) {
        REGION_INDEX = index
        showTrends()
    }

    fun showTrends(){
        val region = regions.keys.toTypedArray().sortedArray()[REGION_INDEX]
        if (trends[region]!=null)
            view.showTrends(trends[region]!!)
    }

    override fun getRegionsMenu(): Array<String> {
        return regions.values.toTypedArray().sortedArray()
    }

    override fun setView(view: GoogleTrendsContract.View) {
        this.view = view
    }

    override fun setRowAndCol(row: Int, column: Int){
        ROW = row
        COLUMN = column

        showTrends()
    }

    override fun getRow(): Int {
        return ROW
    }

    override fun getColumn(): Int {
        return COLUMN
    }
}