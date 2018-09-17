package com.guagua.googletrendsvisualize.model

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor: Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val t1 = System.currentTimeMillis()
        Log.d("HTTP LOG", "${request.url()}, ${chain.connection()}, ${request.headers()}")

        var reponse = chain.proceed(request)
        val t2 = System.currentTimeMillis()
        Log.d("HTTP LOG", "${reponse.request().url()}, response time:${(t2-t1)}ms, ${reponse.headers()}")

        return reponse
    }
}