package com.matias.data.mappers.featureflag

import com.matias.data.entities.featureflag.FeatureFlagsEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.featureflag.FeatureFlagsModel

class FeatureFlagMapper : Mapper<FeatureFlagsEntity, FeatureFlagsModel>() {

	override fun transformEntityToModel(e: FeatureFlagsEntity): FeatureFlagsModel {
		val flagHomeTabEnable = e.flagHomeTabEnable ?: false
		val flagProductsTabEnable = e.flagProductsTabEnable ?: false
		val flagShopCartTabEnable = e.flagShopCartTabEnable ?: false
		val flagContactUsTabEnable = e.flagContactUsTabEnable ?: false
		val flagProfileTabEnable = e.flagProfileTabEnable ?: false
		val flagNotificationsEnable = e.flagNotificationsEnable ?: false
		val flagHomeNoveltyEnable = e.flagHomeNoveltyEnable ?: false
		val flagHomePromotionEnable = e.flagHomePromotionEnable ?: false
		val flagHomeOfferEnable = e.flagHomeOfferEnable ?: false
		val flagHomeServiceEnable = e.flagHomeServiceEnable ?: false

		return FeatureFlagsModel(
			flagHomeTabEnable,
			flagProductsTabEnable,
			flagShopCartTabEnable,
			flagContactUsTabEnable,
			flagProfileTabEnable,
			flagNotificationsEnable,
			flagHomeNoveltyEnable,
			flagHomePromotionEnable,
			flagHomeOfferEnable,
			flagHomeServiceEnable
		)
	}

}