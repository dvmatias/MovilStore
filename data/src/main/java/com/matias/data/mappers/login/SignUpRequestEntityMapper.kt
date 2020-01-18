package com.matias.data.mappers.login

import com.matias.data.entities.login.SignUpRequestEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.login.SignUpRequestModel
import java.text.SimpleDateFormat
import java.util.*

class SignUpRequestEntityMapper : Mapper<SignUpRequestEntity, SignUpRequestModel>() {

	override fun transformEntityToModel(e: SignUpRequestEntity): SignUpRequestModel {
		return transformEntityToModel(e)
	}

	override fun transformModelToEntity(m: SignUpRequestModel): SignUpRequestEntity {
		val email: String = m.email
		val psasword: String = m.psasword
		val userName: String = m.userName
		val birthDate: String = formatDate(m.birthDate)
		val phone: String = m.phone
		val gender: String = m.gender

		return SignUpRequestEntity(email, psasword, userName, birthDate, phone, gender)
	}

	private fun formatDate(date: String): String {
		val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
		val dateFormatted = formatter.parse(date)
		return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(dateFormatted!!)
	}

}