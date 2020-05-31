package com.matias.data.entities.mainposition

import com.google.gson.annotations.SerializedName

data class AnnouncementEntity (
	@SerializedName("title") val title: String?,
	@SerializedName("value") val value: String?
)