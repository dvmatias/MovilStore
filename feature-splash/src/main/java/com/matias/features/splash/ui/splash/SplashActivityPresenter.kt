package com.matias.features.splash.ui.splash

import android.util.Log
import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.splash.GlobalConfigResponseModel
import com.matias.domain.usecases.splash.FetchGlobalConfigUseCase
import javax.inject.Inject

class SplashActivityPresenter @Inject constructor(
        private val fetchGlobalConfigUseCase: FetchGlobalConfigUseCase
) : BasePresenter<SplashActivityContract.View>(), SplashActivityContract.Presenter<SplashActivityContract.View> {

    override fun fetchGlobalConfig() {
        fetchGlobalConfigUseCase(
                { it.either(::handleError, ::handleGlobalConfig) },
                FetchGlobalConfigUseCase.Params("")
        )
    }

    override fun checkUserLoginStatus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleGlobalConfig(globalConfigResponseModel: GlobalConfigResponseModel) {
        view?.let {
            it.animateScreenOut()
            it.goToMainScreen()
        }
    }

    override fun handleError(error: FailureType) {
        Log.d("SplashActivityPresenter", "handleError()")
    }

}