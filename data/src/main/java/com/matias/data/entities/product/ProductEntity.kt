package com.matias.data.entities.product

import com.google.gson.annotations.SerializedName

data class ProductEntity (
	@SerializedName("id") val id: Int?,
	@SerializedName("price") val price: Float?,
	@SerializedName("original_price") val originalPrice: Float?,
	@SerializedName("title") val title: String?,
	@SerializedName("subtitle") val subtitle: String?,
	@SerializedName("description") val description: String?,
	@SerializedName("status") val status: String?,
	@SerializedName("warranty") val warranty: String?,
	@SerializedName("image_url") val imageUrl: String?,
	@SerializedName("thumbnail_url") val thumbnailUrl: String?,
	@SerializedName("secure_thumbnail_url") val secureThumbnailUrl: String?,
	@SerializedName("promotion_image_url") val promotionImageUrl: String?,
	@SerializedName("tags") val tags : String?,
	@SerializedName("quantity") val quantity: QuantityEntity?,
	@SerializedName("multimedia") val multimedia: MultimediaEntity?,
	@SerializedName("rating") val rating: RatingEntity?
) {
	
	data class QuantityEntity(
		@SerializedName("available") val available: Int?,
		@SerializedName("initial") val initial: Int?,
		@SerializedName("sold") val sold: Int?
	)

	data class MultimediaEntity(
		@SerializedName("") val any: Any?
	)

	data class RatingEntity(
		@SerializedName("value") val value: Float?
	)
	
}