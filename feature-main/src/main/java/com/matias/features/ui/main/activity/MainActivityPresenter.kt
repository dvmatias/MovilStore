package com.matias.features.ui.main.activityimport com.matias.core.base.mvp.BasePresenterimport com.matias.domain.usecases.globalconfig.GetFeatureEnableUseCaseimport javax.inject.Injectclass MainActivityPresenter @Inject constructor(	private val getFeatureEnableUseCase: GetFeatureEnableUseCase) : BasePresenter<MainActivityContract.View>(),	MainActivityContract.Presenter<MainActivityContract.View>