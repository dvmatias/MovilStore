package com.matias.domain.models.user

data class UserModel(
	val id: Int,
	val username: String,
	val email: String,
	val password: String,
	val birthDate: String,
	val phone: String,
	val gender: String
)