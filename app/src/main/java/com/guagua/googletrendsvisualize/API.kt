package com.guagua.googletrendsvisualize

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class API <T>{

    var service: Class<T>
    var url: String
    var okHttpClient: OkHttpClient

    @Inject
    constructor (service: Class<T>, url: String, okHttpClient: OkHttpClient){
        this.service = service
        this.url = url
        this.okHttpClient = okHttpClient
    }

    fun provideAPI(): T {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(okHttpClient)
                .build().create(service)
    }

}