package com.matias.core.base.diap.storage

import com.matias.data.cache.featureflag.FeatureFlaghCache
import com.matias.data.cache.featureflag.FeatureFlaghCacheImpl
import com.matias.data.cache.loginstatus.SharedPreferencesCache
import com.matias.data.cache.loginstatus.SharedPreferencesCacheImpl
import com.matias.data.cache.mainposition.MainPositionCache
import com.matias.data.cache.mainposition.MainPositionCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    
    @Provides
    @Singleton
    fun provideFeatureFlaghCache(): FeatureFlaghCache = FeatureFlaghCacheImpl()
    
    @Provides
    @Singleton
    fun provideSharedPreferencesCache(): SharedPreferencesCache = SharedPreferencesCacheImpl()

    @Provides
    @Singleton
    fun provideMainPositionCache(): MainPositionCache = MainPositionCacheImpl()
    
}