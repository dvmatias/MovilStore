package com.matias.domain.base.exception

import com.matias.domain.models.ApiErrorModel

sealed class FailureType {
	object NetworkConnection : FailureType()

	open class LocalErrorType(val errorCode: Int = 327) : FailureType()

	object ServerError : FailureType()

	sealed class LogInErrorType : LocalErrorType() {
		object EmptyCredentialsErrorType : LogInErrorType()
	}

	open class SignUpErrorType : FailureType() {
		class EmptyFieldsLocalException : SignUpErrorType()
		class MalformedEmailServerException(val apiError: ApiErrorModel) : SignUpErrorType()
		class UserAlreadyExistsServerException(val apiError: ApiErrorModel) : SignUpErrorType()
		class UserNameNotAvailableServerException(val apiError: ApiErrorModel) : SignUpErrorType()
		class InvalidBirthDayServerException(val apiError: ApiErrorModel) : SignUpErrorType()
		class InvalidPhoneServerException(val apiError: ApiErrorModel) : SignUpErrorType()
	}

	object FeatureFlagNotFoundError : FailureType()

}