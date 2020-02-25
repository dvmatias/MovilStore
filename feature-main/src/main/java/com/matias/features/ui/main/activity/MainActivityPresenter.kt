package com.matias.features.ui.main.activityimport com.matias.core.base.mvp.BasePresenterimport com.matias.domain.models.featureflag.Featuresimport com.matias.domain.usecases.globalconfig.GetFeatureEnableUseCaseimport javax.inject.Injectclass MainActivityPresenter @Inject constructor(	private val getFeatureEnableUseCase: GetFeatureEnableUseCase) : BasePresenter<MainActivityContract.View>(),	MainActivityContract.Presenter<MainActivityContract.View> {	override fun getTabEnableList(featureList: List<Features>): MutableList<Boolean> {		val tagEnableList: MutableList<Boolean> = mutableListOf()		for (feature in featureList) {			tagEnableList.add(getTabEnable(feature))		}		return tagEnableList	}	private fun getTabEnable(feature: Features): Boolean =		getFeatureEnableUseCase.isFeatureAvailable(GetFeatureEnableUseCase.Params(feature))}