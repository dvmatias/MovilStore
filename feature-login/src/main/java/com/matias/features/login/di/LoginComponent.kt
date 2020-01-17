package com.matias.features.login.di

import com.matias.core.base.diap.base.BaseComponent
import com.matias.features.login.di.fragments.signin.SignInFragmentModule
import com.matias.features.login.di.fragments.signin.SignInFragmentSubcomponent
import com.matias.features.login.di.fragments.signup.SignUpFragmentModule
import com.matias.features.login.di.fragments.signup.SignUpFragmentSubcomponent
import com.matias.features.login.di.login.LoginActivityModule
import com.matias.features.login.di.login.LoginActivitySubComponent
import dagger.Component

@Component(
	dependencies = [(BaseComponent::class)],
	modules = [(LoginModule::class)]
)
@LoginScope
interface LoginComponent {

	fun plus(loginActivityModule: LoginActivityModule): LoginActivitySubComponent

	fun plus(signInFragmentModule: SignInFragmentModule): SignInFragmentSubcomponent

	fun plus(signUpFragmentModule: SignUpFragmentModule): SignUpFragmentSubcomponent

}