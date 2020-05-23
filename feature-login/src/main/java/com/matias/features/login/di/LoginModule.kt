package com.matias.features.login.di

import com.matias.data.service.login.LogInApi
import com.matias.data.service.login.LogInService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule {

	@Provides
	@LoginScope
	fun provideSignInApi(retrofit: Retrofit): LogInApi =
		LogInService(retrofit)

}