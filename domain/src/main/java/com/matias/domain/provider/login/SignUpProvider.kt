package com.matias.domain.provider.login

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.user.UserModel

interface SignUpProvider {

	fun signUp(email: String, psasword: String, userName: String, dateOfBirth: String, phone: String, gender: String): Either<FailureType, UserModel>

}