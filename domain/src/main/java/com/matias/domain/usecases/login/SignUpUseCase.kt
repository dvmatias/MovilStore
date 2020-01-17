package com.matias.domain.usecases.login

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.models.user.UserModel
import com.matias.domain.provider.login.SignUpProvider

class SignUpUseCase(private val signUpProvider: SignUpProvider) : UseCase<UserModel, SignUpUseCase.Params>() {

	override suspend fun run(params: Params): Either<FailureType, UserModel> =
		signUpProvider.signUp(params.email, params.psasword, params.userName, params.dateOfBirth, params.phone, params.gender)

	data class Params(
		val email: String,
		val psasword: String,
		val userName: String,
		val dateOfBirth: String,
		val phone: String,
		val gender: String)

}