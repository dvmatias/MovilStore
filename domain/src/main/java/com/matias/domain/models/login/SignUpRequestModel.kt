package com.matias.domain.models.login

data class SignUpRequestModel (
	val email: String,
	val psasword: String,
	val userName: String,
	val birthDate: String,
	val phone: String,
	val gender: String
)