package com.matias.domain.models.offer

data class ProductOfferModel(
	val id: Int,
	val price: Float,
	val originalPrice: Float,
	val title: String,
	val subtitle: String,
	val thumbnailUrl: String
)