package com.cristhianbonilla.appai

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object NetworkInterceptor {
    fun httpLogging(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}
