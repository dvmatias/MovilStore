package com.matias.data.provider.globalconfig

import com.matias.data.cache.featureflag.FeatureFlaghCache
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.featureflag.Features
import com.matias.domain.provider.globalconfig.FeatureFlagProvider

class FeatureFlagProviderImpl(private val featureFlaghCache: FeatureFlaghCache) : FeatureFlagProvider {

	override fun getFeatureFlagStatus(feature: Features): Either<FailureType, Boolean> {
		featureFlaghCache.getFeatureFlags()?.let {
			return when (feature) {
				Features.TAB_HOME -> Either.Right(it.flagHomeTabEnable)
				Features.TAB_PRODUCTS -> Either.Right(it.flagProductsTabEnable)
				Features.TAB_SHOP_CART -> Either.Right(it.flagShopCartTabEnable)
				Features.TAB_CONTACT_US -> Either.Right(it.flagContactUsTabEnable)
				Features.TAB_PROFILE -> Either.Right(it.flagProfileTabEnable)
				Features.NOTIFICATIONS -> Either.Right(it.flagNotificationsEnable)
			}
		}
		return Either.Left(FailureType.FeatureFlagNotFoundError)
	}

}