package com.matias.core.base.diap.storage

import com.matias.data.cache.featureflag.FeatureFlaghCache
import com.matias.data.cache.featureflag.FeatureFlaghCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideFeatureFlaghCache(): FeatureFlaghCache = FeatureFlaghCacheImpl()

}