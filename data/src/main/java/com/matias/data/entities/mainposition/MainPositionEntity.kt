package com.matias.data.entities.mainposition

import com.google.gson.annotations.SerializedName
import com.matias.data.entities.novelty.NoveltyEntity
import com.matias.data.entities.offer.OfferEntity
import com.matias.data.entities.profile.ProfileEntity
import com.matias.data.entities.promotion.PromotionEntity
import com.matias.data.entities.service.ServiceEntity

data class MainPositionEntity (
	@SerializedName("profile") val profile: ProfileEntity?,
	@SerializedName("novelties_list") val noveltiesList: List<NoveltyEntity>?,
	@SerializedName("promotions_list") val promotionsList: List<PromotionEntity>?,
	@SerializedName("offers_list") val offersList: List<OfferEntity>?,
	@SerializedName("services_list") val servicesList: List<ServiceEntity>?,
	@SerializedName("notifications_last_update_date") val notificationsLastUpdateDate: String?,
	@SerializedName("coupons_last_update_date") val couponsLastUpdateDate: String?
)