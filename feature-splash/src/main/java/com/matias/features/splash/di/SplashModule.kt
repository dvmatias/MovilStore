package com.matias.features.splash.di

import com.matias.data.cache.featureflag.FeatureFlaghCache
import com.matias.data.cache.loginstatus.SharedPreferencesCache
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.globalconfig.GlobalConfigProviderImpl
import com.matias.data.provider.loginstatus.LoginStatusProviderImpl
import com.matias.data.service.splash.SplashApi
import com.matias.data.service.splash.SplashService
import com.matias.domain.provider.globalconfig.GlobalConfigProvider
import com.matias.domain.provider.loginstatus.LoginStatusProvider
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
    fun provideGlobalConfigProvider(
            splashApi: SplashApi,
            featureFlaghCache: FeatureFlaghCache,
            networkHandler: NetworkHandler
    ): GlobalConfigProvider = GlobalConfigProviderImpl(splashApi, featureFlaghCache, networkHandler)

    @Provides
    @SplashScope
    fun provideLoginStatusProvider(
            sharedPreferencesCache: SharedPreferencesCache
    ): LoginStatusProvider = LoginStatusProviderImpl(sharedPreferencesCache)

}