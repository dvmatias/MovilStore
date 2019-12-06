package com.matias.features.splash.di

import com.matias.data.cache.splash.SplashCache
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.splash.SplashProviderImpl
import com.matias.data.service.splash.SplashApi
import com.matias.data.service.splash.SplashService
import com.matias.domain.provider.splash.SplashProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SplashModule {

    @Provides
    @SplashScope
    fun provideSplashApi(retrofit: Retrofit): SplashApi = SplashService(retrofit)

    @Provides
    @SplashScope
    fun provideSplashProvider(
            splashApi: SplashApi,
            splashCache: SplashCache,
            networkHandler: NetworkHandler
    ): SplashProvider =
            SplashProviderImpl(splashApi, splashCache, networkHandler)

}