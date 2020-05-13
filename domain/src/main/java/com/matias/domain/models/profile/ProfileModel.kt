package com.matias.domain.models.profile

data class ProfileModel (
	val id: Int,
	val userName: String,
	val firstName: String,
	val lastName: String,
	val email: String,
	val password: String,
	val birthDate: String,
	val phone: String,
	val gender: UserGender,
	val avatarUrl: String,
	val dni: String,
	val stayLoggedIn: Boolean,
	val createdTime: String,
	val lastUpdatedTime: String,
	val lastLogInTime: String
)