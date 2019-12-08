package com.matias.features.splash.ui.splash

import com.matias.core.base.mvp.BaseContract
import com.matias.domain.models.splash.GlobalConfigResponseModel

interface SplashActivityContract {

	interface View : BaseContract.View {

		fun animateScreenIn()

		fun animateScreenOut()

		fun goToMainScreen()

		fun goToLoginScreen()

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun fetchGlobalConfig()

		fun checkUserLoginStatus()

		fun handleGlobalConfig(globalConfigResponseModel: GlobalConfigResponseModel)

	}

}