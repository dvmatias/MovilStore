package com.matias.features.splash.ui

import com.matias.core.base.BaseApplication
import com.matias.features.splash.di.DaggerSplashComponent
import com.matias.features.splash.di.SplashComponent
import com.matias.features.splash.di.SplashModule

internal class SplashUiComponent {

	companion object {
		internal val component: SplashComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
			createComponent()
		}

		@Suppress("DEPRECATION")
		private fun createComponent(): SplashComponent =
			DaggerSplashComponent.builder()
				.baseComponent(BaseApplication.baseComponent)
				.splashModule(SplashModule())
				.build()
	}

}