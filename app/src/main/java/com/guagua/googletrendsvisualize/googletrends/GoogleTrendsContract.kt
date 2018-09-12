package com.guagua.googletrendsvisualize.googletrends

import com.guagua.googletrendsvisualize.BasePresenter


interface GoogleTrendsContract{

    interface View{
        fun showTrends(trends: Array<String>)
        fun onLoadDataCompleted()
    }

    interface Presenter: BasePresenter<View> {
        fun loadTrendsAndRegions()
        fun getTrendsInRegion(index: Int)
        fun getRegionsMenu(): Array<String>
        fun setRowAndCol(row: Int, column: Int)
        fun getRow(): Int
        fun getColumn(): Int
    }

}