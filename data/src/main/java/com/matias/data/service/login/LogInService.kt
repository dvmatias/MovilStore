package com.matias.data.service.login

import com.matias.data.entities.login.SignUpRequestEntity
import com.matias.data.entities.user.UserEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class LogInService constructor(retrofit: Retrofit) : LogInApi {

	private val logInApi by lazy { retrofit.create(LogInApi::class.java) }

	override fun logIn(username: String, password: String, stayLoggedIn: Boolean): Call<UserEntity> =
		logInApi.logIn(username, password, stayLoggedIn)

	override fun signUp(request: SignUpRequestEntity): Call<UserEntity> = logInApi.signUp(request)

}