package com.matias.data.entities.mainposition

import com.google.gson.annotations.SerializedName
import com.matias.data.entities.novelty.NoveltyEntity
import com.matias.data.entities.product.ProductEntity
import com.matias.data.entities.profile.ProfileEntity
import com.matias.data.entities.promotion.PromotionEntity
import com.matias.data.entities.service.ServiceEntity

data class MainPositionEntity(
	@SerializedName("profile") val profile: ProfileEntity?,
	@SerializedName("novelty_list") val noveltyList: List<NoveltyEntity>?,
	@SerializedName("announcement") val announcement: AnnouncementEntity?,
	@SerializedName("promotion_list") val promotionList: List<PromotionEntity>?,
	@SerializedName("new_product_list") val newProductList: List<ProductEntity>?,
	@SerializedName("offer_product_list") val offerProductList: List<ProductEntity>?,
	@SerializedName("service_list") val serviceList: List<ServiceEntity>?,
	@SerializedName("notifications_last_update_date") val notificationsLastUpdateDate: String?,
	@SerializedName("coupons_last_update_date") val couponsLastUpdateDate: String?
)