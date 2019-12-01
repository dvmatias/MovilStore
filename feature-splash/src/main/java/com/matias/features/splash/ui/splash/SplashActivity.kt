package com.matias.features.splash.ui.splash

import android.os.Bundle
import com.matias.core.base.helpers.startVectorAnimation
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.feature_splash.R
import com.matias.features.splash.di.splash.SplashActivityComponent
import com.matias.features.splash.di.splash.SplashActivityModule
import com.matias.features.splash.ui.SplashUiComponent
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity :
	BasePresenterActivity<SplashActivity, SplashActivityPresenter, SplashActivityComponent>(),
	SplashActivityContract.View {

	override fun bindComponent(): SplashActivityComponent =
		SplashUiComponent.component.plus(SplashActivityModule())

	override fun bindLayout(): Int = R.layout.activity_splash

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
		super.applyImmersiveFullScreen()
	}

	override fun onResume() {
		super.onResume()
		animateScreenIn()
	}

	/*******************************************************************************************************************
	 * [SplashActivityContract.View] implementation
	 */
	override fun animateScreenIn() {
		startVectorAnimation(imageTopLeftFigure)
		startVectorAnimation(imageBottomRightFigure)
	}

	override fun animateScreenOut() {
		startVectorAnimation(imageTopLeftFigure, getDrawable(R.drawable.vd_splash_top_left_figure_anim_out_2))
		startVectorAnimation(imageBottomRightFigure, getDrawable(R.drawable.vd_splash_bottom_right_figure_anim_out_2))
	}
}
