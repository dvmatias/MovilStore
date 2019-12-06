package com.matias.core.base.diap.storage

import com.matias.data.cache.splash.SplashCache
import com.matias.data.cache.splash.SplashCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideSplashCache(): SplashCache = SplashCacheImpl()

}