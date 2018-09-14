package com.guagua.googletrendsvisualize.di

import com.guagua.googletrendsvisualize.googletrends.GoogleTrendsActivity
import com.guagua.googletrendsvisualize.googletrends.GoogleTrendsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule{

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(GoogleTrendsModule::class)])
    abstract fun googleTrendsActivity(): GoogleTrendsActivity

}