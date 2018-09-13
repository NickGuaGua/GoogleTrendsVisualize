package com.guagua.googletrendsvisualize.di

import com.guagua.googletrendsvisualize.googletrends.GoogleTrendsFragment
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(fragment: GoogleTrendsFragment)
}