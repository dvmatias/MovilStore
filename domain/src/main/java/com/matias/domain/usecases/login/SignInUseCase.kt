package com.matias.domain.usecases.login

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.models.user.UserModel
import com.matias.domain.provider.login.SignInProvider

class SignInUseCase(private val signInProvider: SignInProvider) : UseCase<UserModel, SignInUseCase.Params>() {
	
	override suspend fun run(params: Params): Either<FailureType, UserModel> =
			signInProvider.signIn(params.username, params.password, params.staySignedIn)
	
	data class Params(val username: String, val password: String, val staySignedIn: Boolean)
	
}