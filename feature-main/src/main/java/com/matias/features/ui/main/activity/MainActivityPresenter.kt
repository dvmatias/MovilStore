package com.matias.features.ui.main.activityimport com.matias.core.base.mvp.BasePresenterimport com.matias.domain.base.exception.FailureTypeimport com.matias.domain.models.featureflag.Featuresimport com.matias.domain.models.mainposition.MainPositionModelimport com.matias.domain.usecases.globalconfig.GetFeatureEnableUseCaseimport com.matias.domain.usecases.mainposition.FetchMainPositionUseCaseimport javax.inject.Injectclass MainActivityPresenter @Inject constructor(	private val getFeatureEnableUseCase: GetFeatureEnableUseCase,	private val fetchMainPositionUseCase: FetchMainPositionUseCase) : BasePresenter<MainActivityContract.View>(),	MainActivityContract.Presenter<MainActivityContract.View> {	private var isLoadViewFirstTime = true	/*******************************************************************************************************************	 *  [MainActivityContract.Presenter] implementation	 */	override fun getMainPosition() {		view?.apply {			showLoading(true)			fetchMainPositionUseCase(				{ it.either(::onGetMainPositionFailure, ::onGetMainPositionSuccess) },				FetchMainPositionUseCase.Params("")			)		}	}	override fun getTabEnableList(featureList: List<Features>): MutableList<Boolean> {		val tagEnableList: MutableList<Boolean> = mutableListOf()		for (feature in featureList) {			tagEnableList.add(getTabEnable(feature))		}		return tagEnableList	}	private fun onGetMainPositionSuccess(@Suppress("UNUSED_PARAMETER") response: MainPositionModel) {		view?.let {			it.showLoading(false)			if (isLoadViewFirstTime) it.initView() else it.updateMainFragments()			isLoadViewFirstTime = false		}	}	private fun onGetMainPositionFailure(@Suppress("UNUSED_PARAMETER") failure: FailureType) {	}	private fun getTabEnable(feature: Features): Boolean =		getFeatureEnableUseCase.isFeatureAvailable(GetFeatureEnableUseCase.Params(feature))}