package com.matias.features.login.di.fragments.signup

import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.login.SignUpProviderImpl
import com.matias.data.service.login.LogInApi
import com.matias.domain.provider.login.SignUpProvider
import com.matias.domain.usecases.login.SignUpUseCase
import dagger.Module
import dagger.Provides

/**
 * Dagger Module
 *
 * @see [com.matias.features.login.ui.fragments.signup.SignUpFragment] client
 * @see [SignUpFragmentSubcomponent] component
 */
@Module
class SignUpFragmentModule {

	@Provides
	fun provideSignUpProvider(logInApi: LogInApi, networkHandler: NetworkHandler): SignUpProvider =
		SignUpProviderImpl(logInApi, networkHandler)

	@Provides
	fun provideSignUpUseCase(signUpProvider: SignUpProvider): SignUpUseCase =
			SignUpUseCase(signUpProvider)

}