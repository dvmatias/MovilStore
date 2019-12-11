package com.matias.features.splash.di.splash

import com.matias.domain.provider.globalconfig.GlobalConfigProvider
import com.matias.domain.provider.loginstatus.LoginStatusProvider
import com.matias.domain.usecases.globalconfig.FetchGlobalConfigUseCase
import com.matias.domain.usecases.loginstatus.LoginStatusUseCase
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun provideFetchGlobalConfigUseCase(globalConfigProvider: GlobalConfigProvider): FetchGlobalConfigUseCase =
            FetchGlobalConfigUseCase(globalConfigProvider)

    @Provides
    fun provideLoginStatusUseCase(loginStatusProvider: LoginStatusProvider): LoginStatusUseCase =
            LoginStatusUseCase(loginStatusProvider)

}