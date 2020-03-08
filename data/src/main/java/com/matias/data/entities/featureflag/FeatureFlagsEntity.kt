package com.matias.data.entities.featureflag

import com.google.gson.annotations.SerializedName

data class FeatureFlagsEntity(
	@SerializedName("flag_home_tab_enable") val flagHomeTabEnable: Boolean?,
	@SerializedName("flag_products_tab_enable") val flagProductsTabEnable: Boolean?,
	@SerializedName("flag_shop_cart_tab_enable") val flagShopCartTabEnable: Boolean?,
	@SerializedName("flag_contact_us_tab_enable") val flagContactUsTabEnable: Boolean?,
	@SerializedName("flag_profile_tab_enable") val flagProfileTabEnable: Boolean?,
	@SerializedName("flag_notifications_enable") val flagNotificationsEnable: Boolean?,
	@SerializedName("flag_home_novelty_enable") val flagHomeNoveltyEnable: Boolean?,
	@SerializedName("flag_home_promotion_enable") val flagHomePromotionEnable: Boolean?,
	@SerializedName("flag_home_offer_enable") val flagHomeOfferEnable: Boolean?,
	@SerializedName("flag_home_service_enable") val flagHomeServiceEnable: Boolean?
)