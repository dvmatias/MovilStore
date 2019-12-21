package com.matias.domain.models.user

data class UserModel(
		val username: String,
		val email: String,
		val password: String,
		val dateOfBirth: String,
		val phone: String,
		val gender: String
)