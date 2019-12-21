package com.matias.data.mappers.user

import com.matias.data.entities.user.UserEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.user.UserModel

class UserMapper : Mapper<UserEntity, UserModel>() {

	override fun transformEntityToModel(e: UserEntity): UserModel {
		val id: Int = e.id ?: -1
		val username: String = e.username ?: ""
		val email: String = e.email ?: ""
		val password: String = e.password ?: ""
		val dateOfBirth: String = e.dateOfBirth ?: ""
		val phone: String = e.phone ?: ""
		val gender: String = e.gender ?: ""

		return UserModel(id, username, email, password, dateOfBirth, phone, gender)
	}

}