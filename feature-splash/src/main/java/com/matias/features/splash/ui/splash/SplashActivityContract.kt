package com.matias.features.splash.ui.splash

import com.matias.core.base.mvp.BaseContract
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.globalconfig.GlobalConfigModel

interface SplashActivityContract {

	interface View : BaseContract.View {

		fun animateScreenIn()

		fun animateScreenOut()

		fun goToMainScreen()

		fun goToLoginScreen()

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun fetchGlobalConfig()

		fun globalConfigFetchSuccess(globalConfigModel: GlobalConfigModel)

		fun globalConfigFetchFail(failure: FailureType)

		fun checkUserLoginStatus()

		fun onUserLoggedIn(isUserLoggedIn: Boolean)

		fun onUserLoggedOut(failure: FailureType)

	}

}