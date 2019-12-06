package com.matias.features.splash.ui.splash

import android.os.Bundle
import android.os.Handler
import com.matias.core.base.helpers.startVectorAnimation
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.feature_splash.R
import com.matias.features.splash.di.splash.SplashActivitySubComponent
import com.matias.features.splash.di.splash.SplashActivityModule
import com.matias.features.splash.ui.SplashUiComponent
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity :
	BasePresenterActivity<SplashActivity, SplashActivityPresenter, SplashActivitySubComponent>(),
	SplashActivityContract.View {

	override fun bindComponent(): SplashActivitySubComponent =
		SplashUiComponent.component.plus(SplashActivityModule())

	override fun bindLayout(): Int = R.layout.activity_splash

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
		super.applyImmersiveFullScreen()
	}

	override fun onResume() {
		super.onResume()
		presenter.fetchGlobalConfig()
		animateScreenIn()
		Handler().postDelayed({animateScreenOut()}, 5000)
	}

	/*******************************************************************************************************************
	 * [SplashActivityContract.View] implementation
	 */
	override fun animateScreenIn() {
		startVectorAnimation(imageTopLeftFigure, getDrawable(R.drawable.vd_splash_top_left_figure_anim_in_3))
		startVectorAnimation(imageBottomRightFigure, getDrawable(R.drawable.vd_splash_bottom_right_figure_anim_in_3))
	}

	override fun animateScreenOut() {
		startVectorAnimation(imageTopLeftFigure, getDrawable(R.drawable.vd_splash_top_left_figure_anim_out_3))
		startVectorAnimation(imageBottomRightFigure, getDrawable(R.drawable.vd_splash_bottom_right_figure_anim_out_3))
	}

	override fun goToMainScreen() {
		super.showToast("Go to main screen.")
	}

	override fun goToLoginScreen() {
		super.showToast("Go to login screen.")
	}

}