package com.matias.features.di.mainimport com.matias.domain.usecases.globalconfig.GetFeatureEnableUseCaseimport com.matias.features.di.MainScopeimport com.matias.features.ui.main.MainActivityPresenterimport dagger.Moduleimport dagger.Provides@Moduleclass MainActivityModule {	@Provides	fun provideMainActivityPresenter(getFeatureEnableUseCase: GetFeatureEnableUseCase): MainActivityPresenter =		MainActivityPresenter(getFeatureEnableUseCase)}