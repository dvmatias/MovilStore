package com.matias.data.provider.globalconfig

import com.matias.data.cache.featureflag.FeatureFlaghCache
import com.matias.data.mappers.globalconfig.GlobalConfigMapper
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.NetworkProvider
import com.matias.data.service.splash.SplashApi
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.globalconfig.GlobalConfigModel
import com.matias.domain.provider.globalconfig.GlobalConfigProvider

class GlobalConfigProviderImpl(
        private val splashApi: SplashApi,
        private val featureFlaghCache: FeatureFlaghCache,
        networkHandler: NetworkHandler
) : GlobalConfigProvider, NetworkProvider(networkHandler) {

    override fun fetchGlobalConfig(arg: Any): Either<FailureType, GlobalConfigModel> =
            request(splashApi.fetchGlobalConfig()) {
                featureFlaghCache.storeFeatureFlags(it.featureFlagsEntity)
                GlobalConfigMapper().transformEntityToModel(it)
            }

}