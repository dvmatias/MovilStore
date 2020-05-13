package com.matias.domain.usecases.login

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.models.user.UserModel
import com.matias.domain.provider.login.LogInProvider

class LogInUseCase(private val logInProvider: LogInProvider) : UseCase<UserModel, LogInUseCase.Params>() {
	
	override suspend fun run(params: Params): Either<FailureType, UserModel> =
			logInProvider.logIn(params.username, params.password, params.stayLoggedIn)
	
	data class Params(val username: String, val password: String, val stayLoggedIn: Boolean)
	
}