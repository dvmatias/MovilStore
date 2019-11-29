package com.matias.features.splash.ui.splash

import android.os.Bundle
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.feature_splash.R
import com.matias.features.splash.di.splash.SplashActivityComponent
import com.matias.features.splash.di.splash.SplashActivityModule
import com.matias.features.splash.ui.SplashUiComponent

class SplashActivity :
	BasePresenterActivity<SplashActivity, SplashActivityPresenter, SplashActivityComponent>(),
	SplashActivityContract.View {

	override fun bindComponent(): SplashActivityComponent =
		SplashUiComponent.component.plus(SplashActivityModule())

	override fun bindLayout(): Int = R.layout.activity_splash

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
	}

}
