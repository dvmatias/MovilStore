package com.matias.data.provider.siginin

import com.matias.data.mappers.user.UserMapper
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.NetworkProvider
import com.matias.data.service.signin.SignInApi
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.user.UserModel
import com.matias.domain.provider.signin.SignInProvider

class SignInProviderImpl(
		private val signInApi: SignInApi,
		networkHandler: NetworkHandler
) : SignInProvider, NetworkProvider(networkHandler) {
	
	override fun signIn(username: String, password: String): Either<FailureType, UserModel> =
			request(signInApi.signIn(username, password)) {
				UserMapper().transformEntityToModel(it)
			}
	
}