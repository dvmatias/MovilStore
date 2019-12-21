package com.matias.data.entities.user

import com.google.gson.annotations.SerializedName

data class UserEntity(
		@SerializedName("username") val username: String?,
		@SerializedName("email") val email: String?,
		@SerializedName("password") val password: String?,
		@SerializedName("dateOfBirth") val dateOfBirth: String?,
		@SerializedName("phone") val phone: String?,
		@SerializedName("gender") val gender: String?
)