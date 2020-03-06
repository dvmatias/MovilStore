package com.matias.domain.models.featureflag

data class FeatureFlagsModel(
	val flagHomeTabEnable: Boolean,
	val flagProductsTabEnable: Boolean,
	val flagShopCartTabEnable: Boolean,
	val flagContactUsTabEnable: Boolean,
	val flagProfileTabEnable: Boolean,
	val flagNotificationsEnable: Boolean,
	val flagHomeNoveltyEnable: Boolean,
	val flagHomePromotionsEnable: Boolean,
	val flagHomeOffersEnable: Boolean,
	val flagHomeServicesEnable: Boolean
)