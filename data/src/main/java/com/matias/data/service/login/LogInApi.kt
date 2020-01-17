package com.matias.data.service.login

import com.matias.data.entities.user.UserEntity
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LogInApi {

	companion object {
		private const val CONTROLLER_SIGNIN = "user/"

		private const val EP_SIGN_IN = "${CONTROLLER_SIGNIN}signin"
		private const val EP_SIGN_UP = "${CONTROLLER_SIGNIN}signup"

		private const val QUERY_USERNAME = "username"
		private const val QUERY_PASSWORD = "password"
		private const val QUERY_STAY_SIGNED_IN = "stay_signed_in"
		private const val QUERY_EMAIL = "email"
		private const val QUERY_DATE_OF_BIRTH = "date_of_birth"
		private const val QUERY_PHONE = "phone"
		private const val QUERY_GENDER = "gender"
	}

	@POST(EP_SIGN_IN)
	fun signIn(
		@Query(QUERY_USERNAME) username: String,
		@Query(QUERY_PASSWORD) password: String,
		@Query(QUERY_STAY_SIGNED_IN) staySignedIn: Boolean
	): Call<UserEntity>

	@POST(EP_SIGN_UP)
	fun signUp(
		@Query(QUERY_EMAIL) email: String,
		@Query(QUERY_PASSWORD) password: String,
		@Query(QUERY_USERNAME) userName: String,
		@Query(QUERY_DATE_OF_BIRTH) dateOfBirth: String,
		@Query(QUERY_PHONE) phone: String,
		@Query(QUERY_GENDER) gender: String
	): Call<UserEntity>

}