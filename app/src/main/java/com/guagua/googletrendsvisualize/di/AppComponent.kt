package com.guagua.googletrendsvisualize.di

import android.app.Application
import com.guagua.googletrendsvisualize.GoogleTrendsApplication
import com.guagua.googletrendsvisualize.model.GoogleTrendsRepository
import com.guagua.googletrendsvisualize.model.GoogleTrendsRepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(GoogleTrendsRepositoryModule::class), (ActivityBindingModule::class), (AndroidSupportInjectionModule::class)])
interface AppComponent : AndroidInjector<GoogleTrendsApplication> {

    fun getGoogleTrendsRepository(): GoogleTrendsRepository

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): AppComponent.Builder
    }

}