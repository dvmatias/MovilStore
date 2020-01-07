package com.matias.data.service.signin

import com.matias.data.entities.user.UserEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class SignInService constructor(retrofit: Retrofit): SignInApi {
	
	private val signInApi by lazy { retrofit.create(SignInApi::class.java) }
	
	override fun signIn(username: String, password: String, staySignedIn: Boolean): Call<UserEntity> =
			signInApi.signIn(username, password, staySignedIn)
		
}