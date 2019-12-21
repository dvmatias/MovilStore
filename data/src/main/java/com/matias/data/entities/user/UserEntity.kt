package com.matias.data.entities.user

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserEntity(
	@SerializedName("id") val id: Int?,
	@SerializedName("user_name") val username: String?,
	@SerializedName("first_name") val firstName: String?,
	@SerializedName("last_name") val lastName: String?,
	@SerializedName("email") val email: String?,
	@SerializedName("password") val password: String?,
	@SerializedName("date_of_birth") val dateOfBirth: String?,
	@SerializedName("phone") val phone: String?,
	@SerializedName("gender") val gender: String?,
	@SerializedName("image") val image: String?,
	@SerializedName("dni") val dni: String?,
	@SerializedName("createdTime") val createdTime: Date,
	@SerializedName("updatedTime") val updatedTime: Date?
)