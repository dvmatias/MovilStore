package com.matias.features.splash.ui.splash

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
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

		val wrappedDrawable = DrawableCompat.wrap(imageTopLeftFigure.drawable)
		//DrawableCompat.setTint(wrappedDrawable, _iconColor!!)
		imageTopLeftFigure.setImageDrawable(wrappedDrawable)
		val d = imageTopLeftFigure.drawable
		if (d is AnimatedVectorDrawableCompat) d.start()
		else if (d is AnimatedVectorDrawable) d.start()
	}

}
