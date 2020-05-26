package com.matias.domain.models.newproduct

data class NewProductModel(
	val id: Int,
	val price: Float,
	val title: String,
	val subtitle: String,
	val promotionImageUrl: String
)