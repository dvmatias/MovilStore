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
				Features.FILTER_SEARCH_MAIN -> Either.Right(it.mainFilterSearchEnable)
				Features.SHOP_CART_MAIN -> Either.Right(it.mainShopCartEnable)
				Features.OFFERS_DISCOUNTS_COUPONS_MAIN -> Either.Right(it.mainOffersDiscountsCouponsEnable)
			}
		}
		return Either.Left(FailureType.FeatureFlagNotFoundError)
	}

}