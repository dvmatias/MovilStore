package com.matias.data.entities.novelty

import com.google.gson.annotations.SerializedName

data class NoveltyEntity(
	@SerializedName("id") val id: Int?,
	@SerializedName("image_url") val imageUrl: String?,
	@SerializedName("title") val title: String?,
	@SerializedName("subtitle") val subtitle: String?,
	@SerializedName("description") val description: String?,
	@SerializedName("terms_and_conditions") val termsAndConditions: String?,
	@SerializedName("active") val active: Boolean?, // Map only this novelty in moedl if the novelty is active.
	@SerializedName("background_color") val backgroundColor: String? // Map only this novelty in moedl if the novelty is active.
)