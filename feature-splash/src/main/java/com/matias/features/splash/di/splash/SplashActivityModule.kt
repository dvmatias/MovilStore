package com.matias.features.splash.di.splash

import com.matias.domain.provider.splash.SplashProvider
import com.matias.domain.usecases.splash.FetchGlobalConfigUseCase
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

	@Provides
    fun provideFetchGlobalConfigUseCase(splashProvider: SplashProvider): FetchGlobalConfigUseCase =
            FetchGlobalConfigUseCase(splashProvider)

}