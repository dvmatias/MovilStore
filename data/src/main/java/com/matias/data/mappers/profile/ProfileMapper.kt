package com.matias.data.mappers.profile

import com.matias.data.entities.profile.ProfileEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.profile.ProfileModel
import com.matias.domain.models.profile.UserGender

private const val INT_DEFAULT = -1
private const val STRING_DEFAULT = ""
private const val BOOLEAN_DEFAULT = false

class ProfileMapper : Mapper<ProfileEntity, ProfileModel>() {

	override fun transformEntityToModel(e: ProfileEntity): ProfileModel {
		val id: Int = e.id ?: INT_DEFAULT
		val userName: String = e.userName ?: STRING_DEFAULT
		val firstName: String = e.firstName ?: STRING_DEFAULT
		val lastName: String = e.lastName ?: STRING_DEFAULT
		val email: String = e.email ?: STRING_DEFAULT
		val password: String = e.password ?: STRING_DEFAULT
		val birthDate: String = e.birthDate ?: STRING_DEFAULT
		val phone: String = e.phone ?: STRING_DEFAULT
		val gender: UserGender = if (e.gender.equals("male")) UserGender.MALE else UserGender.FEMALE
		val avatarUrl: String = e.avatarUrl ?: STRING_DEFAULT
		val dni: String = e.dni ?: STRING_DEFAULT
		val stayLoggedIn: Boolean = e.stayLoggedIn?.let { it == 1 } ?: false
		val createdTime: String = e.createdTime ?: STRING_DEFAULT
		val lastUpdatedTime: String = e.lastUpdatedTime ?: STRING_DEFAULT
		val lastLogInTime: String = e.lastLogInTime ?: STRING_DEFAULT

		return ProfileModel(id, userName, firstName, lastName, email, password, birthDate, phone, gender, avatarUrl, dni, stayLoggedIn, createdTime, lastUpdatedTime, lastLogInTime)
	}

}