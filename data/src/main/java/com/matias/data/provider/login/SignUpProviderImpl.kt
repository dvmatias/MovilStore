package com.matias.data.provider.login

import com.matias.data.mappers.login.SignUpRequestEntityMapper
import com.matias.data.mappers.user.UserMapper
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.NetworkProvider
import com.matias.data.service.login.LogInApi
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.login.SignUpRequestModel
import com.matias.domain.models.user.UserModel
import com.matias.domain.provider.login.SignUpProvider

class SignUpProviderImpl(
	private val logInApi: LogInApi,
	networkHandler: NetworkHandler
) : SignUpProvider, NetworkProvider(networkHandler) {

	override fun signUp(email: String, psasword: String, userName: String, birthDate: String, phone: String, gender: String): Either<FailureType, UserModel> {
		val model = SignUpRequestModel(email, psasword, userName, birthDate, phone, gender)

		return request(logInApi.signUp(SignUpRequestEntityMapper().transformModelToEntity(model))) {
			UserMapper().transformEntityToModel(it)
		}
	}
}