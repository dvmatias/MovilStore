package com.matias.features.ui.main.fragments.homeimport com.matias.core.base.mvp.BasePresenterimport javax.inject.Injectclass MainHomeFragmentPresenter @Inject constructor() : BasePresenter<MainHomeFragmentContract.View>(),	MainHomeFragmentContract.Presenter<MainHomeFragmentContract.View> {}