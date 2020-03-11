package com.matias.data.service.login

import com.matias.data.entities.login.SignUpRequestEntity
import com.matias.data.entities.user.UserEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.logging.Logger

interface LogInApi {

	companion object {
		private const val CONTROLLER_SIGNIN = "user"

		private const val EP_LOG_IN = "$CONTROLLER_SIGNIN/login"
		private const val EP_SIGN_UP = "$CONTROLLER_SIGNIN/signup"

		private const val QUERY_USERNAME = "username"
		private const val QUERY_PASSWORD = "password"
		private const val QUERY_STAY_LOGGED_IN = "stay_signed_in"
	}

	@POST(EP_LOG_IN)
	fun logIn(
		@Query(QUERY_USERNAME) username: String,
		@Query(QUERY_PASSWORD) password: String,
		@Query(QUERY_STAY_LOGGED_IN) stayLoggedIn: Boolean
	): Call<UserEntity>

	@POST(EP_SIGN_UP)
	fun signUp(@Body request: SignUpRequestEntity): Call<UserEntity>

}