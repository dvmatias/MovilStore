package com.matias.domain.models.product

data class ProductModel(
	val id: Int,
	val price: Float,
	val originalPrice: Float,
	val title: String,
	val subtitle: String,
	val description: String,
	val status: StatusEnum,
	val warranty: String,
	val imageUrl: String,
	val thumbnailUrl: String,
	val secureThumbnailUrl: String,
	val promotionImageUrl: String,
	val tags: ArrayList<String>,
	val quantity: QuantityModel,
	val images: List<ImageModel>,
	val rating: RatingModel
) {

	data class QuantityModel(
		val available: Int,
		val sold: Int
	)

	data class ImageModel(
		val url: String
	)

	data class RatingModel(
		val value: Float,
		val quantity: Int
	)

}