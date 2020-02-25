package com.matias.features.splash.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.core.helpers.VectorDrawableHelper
import com.matias.feature_splash.R
import com.matias.features.splash.di.splash.SplashActivityModule
import com.matias.features.splash.di.splash.SplashActivitySubComponent
import com.matias.features.splash.ui.SplashUiComponent
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject


class SplashActivity :
	BasePresenterActivity<SplashActivity, SplashActivityPresenter, SplashActivitySubComponent>(),
	SplashActivityContract.View {

	@Inject
	lateinit var vectorDrawableHelper: VectorDrawableHelper

	override fun bindComponent(): SplashActivitySubComponent =
		SplashUiComponent.component.plus(SplashActivityModule())

	override fun bindLayout(): Int =
		R.layout.activity_splash

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
		super.applyImmersiveFullScreen()
	}

	override fun onResume() {
		super.onResume()
//		navigator.toMainScreen(this, null, null, true)
		animateScreenIn()
		Handler().postDelayed({ presenter.fetchGlobalConfig() }, 1100)
	}

	/*******************************************************************************************************************
	 * [SplashActivityContract.View] implementation
	 */
	override fun animateScreenIn() {
		vectorDrawableHelper.startVectorAnimation(
			imageTopLeftFigure,
			getDrawable(R.drawable.vd_splash_top_left_figure_anim_in_3)
		)
		vectorDrawableHelper.startVectorAnimation(
			imageBottomRightFigure,
			getDrawable(R.drawable.vd_splash_bottom_right_figure_anim_in_3)
		)
	}

	override fun animateScreenOut() {
		vectorDrawableHelper.startVectorAnimation(
			imageTopLeftFigure,
			getDrawable(R.drawable.vd_splash_top_left_figure_anim_out_3)
		)
		vectorDrawableHelper.startVectorAnimation(
			imageBottomRightFigure,
			getDrawable(R.drawable.vd_splash_bottom_right_figure_anim_out_3)
		)
	}

	override fun goToMainScreen() {
		super.showToast("Go to main screen.")
		finish()
	}

	override fun goToLoginScreen() {
		val options = ActivityOptionsCompat
			.makeSceneTransitionAnimation(this, imageLogoName as View, "image_logo_name")
		Handler().postDelayed({
			navigator.toLoginScreen(this, null, options, false)
			Handler().postDelayed({ this@SplashActivity.finish() }, 500)
		}, 500)
	}

}