package com.matias.domain.base.exception

sealed class FailureType {
    object NetworkConnection : FailureType()
	class SignInEmptyCredentialsError(): FailureType()
    class ServerError(val errorCode: Int = 400) : FailureType()
    class LocalError(val errorCode: Int = 327) : FailureType()

	sealed class SignUpError : FailureType() {
		object EmmptyFields: SignUpError()
	}
}