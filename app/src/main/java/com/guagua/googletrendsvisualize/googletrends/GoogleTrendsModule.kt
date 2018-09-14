package com.guagua.googletrendsvisualize.googletrends

import com.guagua.googletrendsvisualize.di.ActivityScoped
import com.guagua.googletrendsvisualize.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GoogleTrendsModule {

    @ContributesAndroidInjector
    abstract fun googleTrendsFragment(): GoogleTrendsFragment

    @Binds
    abstract fun googleTrendsPresenter(presenter: GoogleTrendsPresenter): GoogleTrendsContract.Presenter

}