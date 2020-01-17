package com.matias.data.service.login

import com.matias.data.entities.user.UserEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class LogInService constructor(retrofit: Retrofit) : LogInApi {

	private val logInApi by lazy { retrofit.create(LogInApi::class.java) }

	override fun signIn(username: String, password: String, staySignedIn: Boolean): Call<UserEntity> =
		logInApi.signIn(username, password, staySignedIn)

	override fun signUp(email: String, password: String, userName: String, dateOfBirth: String, phone: String, gender: String): Call<UserEntity> =
		logInApi.signUp(email, password, userName, dateOfBirth, phone, gender)

}