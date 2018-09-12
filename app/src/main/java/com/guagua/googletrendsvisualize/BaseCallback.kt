package com.guagua.googletrendsvisualize

interface BaseCallback<T>{
    fun onDataReturn(data: T)
    fun onDataNotAvailable()
}