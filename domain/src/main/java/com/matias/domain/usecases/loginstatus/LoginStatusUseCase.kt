package com.matias.domain.usecases.loginstatus

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.provider.loginstatus.LoginStatusProvider

class LoginStatusUseCase(
	private val loginStatusProvider: LoginStatusProvider
) : UseCase<Boolean, LoginStatusUseCase.Params>() {

	override suspend fun run(params: Params): Either<FailureType, Boolean> =
		loginStatusProvider.getLoginStatus()

	data class Params(val any: Any)

}