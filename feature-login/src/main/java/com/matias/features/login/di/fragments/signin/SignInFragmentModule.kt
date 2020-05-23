package com.matias.features.login.di.fragments.signin

import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.login.LogInProviderImpl
import com.matias.data.service.login.LogInApi
import com.matias.domain.provider.login.LogInProvider
import com.matias.domain.usecases.login.LogInUseCase
import com.matias.features.login.ui.fragments.signin.SignInFragment
import dagger.Module
import dagger.Provides

/**
 * Dagger Module
 *
 * @see [SignInFragment] client
 * @see [SignInFragmentSubcomponent] component
 */
@Module
class SignInFragmentModule {

	@Provides
	fun provideLogInProvider(
		logInApi: LogInApi,
		networkHandler: NetworkHandler
	): LogInProvider = LogInProviderImpl(logInApi, networkHandler)

	@Provides
	fun provideSignInUseCase(logInProvider: LogInProvider): LogInUseCase =
		LogInUseCase(logInProvider)

}