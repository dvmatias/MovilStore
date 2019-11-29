package com.matias.features.splash.ui.splash

import com.matias.core.base.mvp.BaseContract

interface SplashActivityContract {

	interface View : BaseContract.View {

		fun animateScreenIn()

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

	}

}