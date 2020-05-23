package com.matias.features.login.ui

import com.matias.core.base.BaseApplication
import com.matias.features.login.di.DaggerLoginComponent
import com.matias.features.login.di.LoginComponent
import com.matias.features.login.di.LoginModule

internal class LoginUiComponent {

	companion object {
		internal val component: LoginComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
			createComponent()
		}

		@Suppress("DEPRECATION")
		private fun createComponent(): LoginComponent =
			DaggerLoginComponent.builder()
				.baseComponent(BaseApplication.baseComponent)
				.loginModule(LoginModule())
				.build()
	}

}