package com.matias.data.entities.profile

import com.google.gson.annotations.SerializedName

data class ProfileEntity (
	@SerializedName("id") val id: Int?,
	@SerializedName("user_name") val userName: String?,
	@SerializedName("first_name") val firstName: String?,
	@SerializedName("last_name") val lastName: String?,
	@SerializedName("email") val email: String?,
	@SerializedName("password") val password: String?,
	@SerializedName("birth_date") val birthDate: String?,
	@SerializedName("phone") val phone: String?,
	@SerializedName("gender") val gender: String?,
	@SerializedName("avatar_url") val avatarUrl: String?,
	@SerializedName("dni") val dni: String?,
	@SerializedName("stay_logged_in") val stayLoggedIn: Boolean?,
	@SerializedName("created_time") val createdTime: String?,
	@SerializedName("last_updated_time") val lastUpdatedTime: String?,
	@SerializedName("last_log_in_time") val lastLogInTime: String?
)