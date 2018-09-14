package com.guagua.googletrendsvisualize

import com.guagua.googletrendsvisualize.di.DaggerAppComponent
import com.guagua.googletrendsvisualize.model.GoogleTrendsRepository
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class GoogleTrendsApplication: DaggerApplication() {

    @Inject
    lateinit var googleTrendsRepository: GoogleTrendsRepository

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}