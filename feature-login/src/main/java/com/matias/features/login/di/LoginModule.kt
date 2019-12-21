package com.matias.features.login.di

import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.siginin.SignInProviderImpl
import com.matias.data.service.signin.SignInApi
import com.matias.data.service.signin.SignInService
import com.matias.domain.provider.signin.SignInProvider
import com.matias.domain.usecases.sigin.SignInUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule {

	@Provides
	@LoginScope
	fun provideSignInApi(retrofit: Retrofit): SignInApi = SignInService(retrofit)

	@Provides
	@LoginScope
	fun provideSignInProvider(
		signInApi: SignInApi,
		networkHandler: NetworkHandler
	): SignInProvider = SignInProviderImpl(signInApi, networkHandler)

	@Provides
	@LoginScope
	fun provideSignInUseCase(signInProvider: SignInProvider): SignInUseCase =
		SignInUseCase(signInProvider)

}