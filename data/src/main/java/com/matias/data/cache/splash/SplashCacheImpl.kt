package com.matias.data.cache.splash

import android.util.Log

class SplashCacheImpl : SplashCache {

    override fun doSomethingOnCache(): Any {
        Log.d("SplashCacheImpl", "doSomethingOnCache()")
        return "return any"
    }
}