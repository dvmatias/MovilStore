package com.matias.features.ui.main.activityimport com.matias.core.base.mvp.BasePresenterimport com.matias.domain.base.exception.FailureTypeimport com.matias.domain.models.mainposition.AnnouncementModelimport com.matias.domain.models.mainposition.MainPositionModelimport com.matias.domain.models.newproduct.NewProductModelimport com.matias.domain.models.novelty.NoveltyModelimport com.matias.domain.models.offer.OfferProductModelimport com.matias.domain.usecases.globalconfig.GetFeatureEnableUseCaseimport com.matias.domain.usecases.mainposition.FetchMainPositionUseCaseimport com.matias.domain.usecases.mainposition.GetNewProductListUseCaseimport com.matias.domain.usecases.mainposition.GetNoveltyListUseCaseimport com.matias.domain.usecases.mainposition.GetOfferProductListUseCaseimport javax.inject.Injectclass MainActivityPresenter @Inject constructor(	private val getFeatureEnableUseCase: GetFeatureEnableUseCase,	private val fetchMainPositionUseCase: FetchMainPositionUseCase,	private val getNoveltyListUseCase: GetNoveltyListUseCase,	private val getNewProductListUseCase: GetNewProductListUseCase,	private val getOfferProductListUseCase: GetOfferProductListUseCase	) : BasePresenter<MainActivityContract.View>(),	MainActivityContract.Presenter<MainActivityContract.View> {	private var isLoadViewFirstTime = true	/*******************************************************************************************************************	 *  [MainActivityContract.Presenter] implementation	 */	override fun getMainPosition() {		view?.apply {			showLoadingScreen()			fetchMainPositionUseCase(				{ it.either(::onGetMainPositionFailure, ::onGetMainPositionSuccess) },				FetchMainPositionUseCase.Params("")			)		}	}	override fun getNoveltyList(): List<NoveltyModel> =		getNoveltyListUseCase.get()	override fun getAnnouncement(): AnnouncementModel? {		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.	}	override fun getNewProductList(): ArrayList<NewProductModel> =		getNewProductListUseCase.get()	override fun getProductOfferList(): ArrayList<OfferProductModel> =		getOfferProductListUseCase.get()	private fun onGetMainPositionSuccess(@Suppress("UNUSED_PARAMETER") response: MainPositionModel) {		view?.let {			if (isLoadViewFirstTime) it.initView() else it.updateView()			isLoadViewFirstTime = false			it.showInfoScreen()		}	}	private fun onGetMainPositionFailure(@Suppress("UNUSED_PARAMETER") failure: FailureType) {	}}