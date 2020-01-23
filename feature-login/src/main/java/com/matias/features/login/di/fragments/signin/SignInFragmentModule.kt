package com.matias.features.login.di.fragments.signin

import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.login.SignInProviderImpl
import com.matias.data.service.login.LogInApi
import com.matias.domain.provider.login.SignInProvider
import com.matias.domain.usecases.login.SignInUseCase
import com.matias.features.login.di.LoginScope
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
	fun provideSignInProvider(
		logInApi: LogInApi,
		networkHandler: NetworkHandler
	): SignInProvider = SignInProviderImpl(logInApi, networkHandler)

	@Provides
	fun provideSignInUseCase(signInProvider: SignInProvider): SignInUseCase =
		SignInUseCase(signInProvider)

}