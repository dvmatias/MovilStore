package com.matias.features.splash.ui.splash

import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.globalconfig.GlobalConfigModel
import com.matias.domain.usecases.splash.FetchGlobalConfigUseCase
import javax.inject.Inject

class SplashActivityPresenter @Inject constructor(
        private val fetchGlobalConfigUseCase: FetchGlobalConfigUseCase
) : BasePresenter<SplashActivityContract.View>(), SplashActivityContract.Presenter<SplashActivityContract.View> {

    override fun fetchGlobalConfig() {
        fetchGlobalConfigUseCase(
                { it.either(::handleError, ::globalConfigFetchSuccess) },
                FetchGlobalConfigUseCase.Params("")
        )
    }

    override fun globalConfigFetchSuccess(globalConfigModel: GlobalConfigModel) {
        checkUserLoginStatus()
    }

    override fun globalConfigFetchFail(e: FailureType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkUserLoginStatus() {

    }

    override fun onUserLoggedIn() {
        view?.apply {
            animateScreenOut()
            goToMainScreen()
        }
    }

    override fun onUserLoggedOut() {
        view?.apply {
            animateScreenOut()
            goToLoginScreen()
        }
    }

    override fun onUserNotExistent() {
        view?.apply {
            animateScreenOut()
            goToLoginScreen()
        }
    }
}