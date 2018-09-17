package com.guagua.googletrendsvisualize

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.guagua.googletrendsvisualize.googletrends.GoogleTrendsContract
import com.guagua.googletrendsvisualize.googletrends.GoogleTrendsPresenter
import com.guagua.googletrendsvisualize.model.GoogleTrendsDataSource
import com.guagua.googletrendsvisualize.model.GoogleTrendsRepository
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.FileReader

class GoogleTrendsPresenterTest{

    @Mock
    lateinit var googleTrendsRepository: GoogleTrendsRepository

    @Mock
    lateinit var googleTrendsFragment: GoogleTrendsContract.View

    lateinit var googleTrendsPresenter: GoogleTrendsPresenter

    lateinit var TRENDS: HashMap<String, Array<String>>
    lateinit var REGIONS: HashMap<String, String>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        googleTrendsPresenter = GoogleTrendsPresenter(googleTrendsRepository)
        googleTrendsPresenter.setView(googleTrendsFragment)

        val TYPE1 = object : TypeToken<HashMap<String, Array<String>>>(){}.type
        val jsonReader1 = JsonReader(FileReader("../google-trends.json"))
        TRENDS = Gson().fromJson(jsonReader1, TYPE1)

        val TYPE2 = object : TypeToken<HashMap<String, String>>(){}.type
        val jsonReader2 = JsonReader(FileReader("../google-trends-regions.json"))
        REGIONS = Gson().fromJson(jsonReader2, TYPE2)
    }

    @Test
    fun loadGoogleTrends(){
//        val countDownLatch: CountDownLatch = CountDownLatch(1)

        googleTrendsPresenter.loadTrendsAndRegions()

        argumentCaptor<GoogleTrendsDataSource.GetAllTrendsCallback>().apply{
            verify(googleTrendsRepository).getAllTrends(capture())
            lastValue.onDataReturn(TRENDS)
        }

        argumentCaptor<GoogleTrendsDataSource.GetAllRegionCallback>().apply{
            verify(googleTrendsRepository).getAllRegions(capture())
            lastValue.onDataReturn(REGIONS)
        }

        verify(googleTrendsFragment).onLoadDataCompleted()
        googleTrendsPresenter.getTrendsInRegion(3)
        verify(googleTrendsFragment).showTrends(TRENDS[REGIONS.keys.toTypedArray().sortedArray()[3]]!!)

    }

}