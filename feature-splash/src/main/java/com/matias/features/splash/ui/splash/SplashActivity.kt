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

		val wrappedDrawableTop = DrawableCompat.wrap(imageTopLeftFigure.drawable)
		//DrawableCompat.setTint(wrappedDrawable, _iconColor!!)
		imageTopLeftFigure.setImageDrawable(wrappedDrawableTop)
		val d1 = imageTopLeftFigure.drawable
		if (d1 is AnimatedVectorDrawableCompat) d1.start()
		else if (d1 is AnimatedVectorDrawable) d1.start()



		val wrappedDrawableBottom = DrawableCompat.wrap(imageBottomRightFigure.drawable)
		//DrawableCompat.setTint(wrappedDrawable, _iconColor!!)
		imageBottomRightFigure.setImageDrawable(wrappedDrawableBottom)
		val d2 = imageBottomRightFigure.drawable
		if (d2 is AnimatedVectorDrawableCompat) d2.start()
		else if (d2 is AnimatedVectorDrawable) d2.start()
	}

}
