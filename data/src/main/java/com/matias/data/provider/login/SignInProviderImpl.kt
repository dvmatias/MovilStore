package com.matias.data.provider.login

import com.matias.data.mappers.user.UserMapper
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.NetworkProvider
import com.matias.data.service.login.LogInApi
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.user.UserModel
import com.matias.domain.provider.login.SignInProvider

class SignInProviderImpl(
	private val logInApi: LogInApi,
	networkHandler: NetworkHandler
) : SignInProvider, NetworkProvider(networkHandler) {
	
	override fun signIn(username: String, password: String, staySignedIn: Boolean): Either<FailureType, UserModel> =
			request(logInApi.signIn(username, password, staySignedIn)) {
				UserMapper().transformEntityToModel(it)
			}
	
}