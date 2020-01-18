package com.matias.domain.base.exception

sealed class FailureType {
	object NetworkConnection : FailureType()
    class ServerError(val errorCode: Int = 400) : FailureType()
    class LocalError(val errorCode: Int = 327) : FailureType()


	sealed class SignInError : FailureType() {
		object EmptyCredentialsError: SignInError()
	}

	sealed class SignUpError(val errorCode: Int) : FailureType() {
		class EmptyFieldsLocalException: SignUpError(500)
		class MalformedEmailServerException: SignUpError(501)
		class UserAlreadyExistsServerException: SignUpError(502)
		class UserNameNotAvailableServerException: SignUpError(503)
		class NotValidBirthDayServerException: SignUpError(504)
		class NotValidPhoneServerException: SignUpError(505)
	}

}