package com.matias.data.provider.globalconfig

import com.matias.data.cache.featureflag.FeatureFlaghCache
import com.matias.domain.models.featureflag.Features
import com.matias.domain.provider.globalconfig.FeatureFlagProvider

class FeatureFlagProviderImpl(private val featureFlaghCache: FeatureFlaghCache) : FeatureFlagProvider {

	override fun getFeatureFlagStatus(feature: Features): Boolean {
		featureFlaghCache.getFeatureFlags()?.let {
			return when (feature) {
				Features.TAB_HOME -> it.flagHomeTabEnable
				Features.TAB_PRODUCTS -> it.flagProductsTabEnable
				Features.TAB_SHOP_CART -> it.flagShopCartTabEnable
				Features.TAB_CONTACT_US -> it.flagContactUsTabEnable
				Features.TAB_PROFILE -> it.flagProfileTabEnable
				Features.NOTIFICATIONS -> it.flagNotificationsEnable
				Features.HOME_NOVELTY -> it.flagHomeNoveltyEnable
				Features.HOME_PROMOTION -> it.flagHomePromotionEnable
				Features.HOME_OFFER -> it.flagHomeOfferEnable
				Features.HOME_SERVICE -> it.flagHomeServiceEnable
			}
		}
		return false
	}

}