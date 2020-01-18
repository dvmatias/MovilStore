package com.matias.data.entities.login

import com.google.gson.annotations.SerializedName

data class SignUpRequestEntity (
	@SerializedName("email") val email: String,
	@SerializedName("password") val psasword: String,
	@SerializedName("user_name") val userName: String,
	@SerializedName("birth_date") val birthDate: String,
	@SerializedName("phone") val phone: String,
	@SerializedName("gender") val gender: String
)