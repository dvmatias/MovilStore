package com.matias.domain.models.novelty

data class NoveltyModel(
	val id: Int,
	val imageUrl: String,
	val title: String,
	val subtitle: String,
	val description: String,
	val termsAndConditions: String,
	val backgroundColor: String
)