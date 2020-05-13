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
	@SerializedName("birth_date") val birthDate: String?,
	@SerializedName("phone") val phone: String?,
	@SerializedName("gender") val gender: String?,
	@SerializedName("avatar_url") val avatar: String?,
	@SerializedName("dni") val dni: String?,
	@SerializedName("stay_logged_in") val stayLoggedIn: Int?,
	@SerializedName("created_time") val createdTime: Date?,
	@SerializedName("last_updated_time") val updatedTime: Date?,
	@SerializedName("last_log_in_time") val lastLogInTime: Date?
)