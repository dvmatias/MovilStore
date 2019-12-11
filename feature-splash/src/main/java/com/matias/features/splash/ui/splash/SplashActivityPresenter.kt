package com.matias.features.splash.ui.splash

import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.globalconfig.GlobalConfigModel
import com.matias.domain.usecases.globalconfig.FetchGlobalConfigUseCase
import com.matias.domain.usecases.loginstatus.LoginStatusUseCase
import javax.inject.Inject

class SplashActivityPresenter @Inject constructor(
        private val fetchGlobalConfigUseCase: FetchGlobalConfigUseCase,
        private val loginStatusUseCase: LoginStatusUseCase
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
        view?.apply {
            loginStatusUseCase(
                    { it.either(::onUserLoggedOut, ::onUserLoggedIn) },
                    LoginStatusUseCase.Params("")
            )
        }
    }

    override fun onUserLoggedIn(isUserLoggedIn: Boolean) {
        view?.apply {
            animateScreenOut()
            goToMainScreen()
        }
    }

    override fun onUserLoggedOut(e: FailureType) {
        view?.apply {
            animateScreenOut()
            goToLoginScreen()
        }
    }

}