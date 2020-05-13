package com.matias.data.provider.login

import com.matias.data.mappers.user.UserMapper
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.NetworkProvider
import com.matias.data.service.login.LogInApi
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.user.UserModel
import com.matias.domain.provider.login.LogInProvider

class LogInProviderImpl(
	private val logInApi: LogInApi,
	networkHandler: NetworkHandler
) : LogInProvider, NetworkProvider(networkHandler) {
	
	override fun logIn(username: String, password: String, stayLoggedIn: Boolean): Either<FailureType, UserModel> =
			request(logInApi.logIn(username, password, stayLoggedIn)) {
				UserMapper().transformEntityToModel(it)
			}
	
}