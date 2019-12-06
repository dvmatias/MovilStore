package com.matias.data.provider.splash

import com.matias.data.cache.splash.SplashCache
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.NetworkProvider
import com.matias.data.service.splash.SplashApi
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.provider.splash.SplashProvider

class SplashProviderImpl(
        private val splashApi: SplashApi,
        private val splashCache: SplashCache,
        networkHandler: NetworkHandler
) : SplashProvider, NetworkProvider(networkHandler){

    override fun doSomething(arg: Any): Either<FailureType, Unit> =
            request(splashApi.doSomethingOnServer()) {
                Unit
            }

}