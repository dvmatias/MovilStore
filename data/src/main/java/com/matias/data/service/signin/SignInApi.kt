package com.matias.data.service.signin

import com.matias.data.entities.user.UserEntity
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface SignInApi {

	companion object {
		private const val CONTROLLER_SIGNIN = "user/"

		private const val EP_SIGN_IN = "${CONTROLLER_SIGNIN}signin"

		private const val QUERY_USERNAME = "username"
		private const val QUERY_PASSWORD = "password"
	}

	@POST(EP_SIGN_IN)
	fun signIn(
		@Query(QUERY_USERNAME) username: String,
		@Query(QUERY_PASSWORD) password: String
	): Call<UserEntity>

}