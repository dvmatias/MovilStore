package com.matias.features.ui.main.activityimport com.matias.core.base.mvp.BaseContractimport com.matias.domain.models.featureflag.Featuresimport com.matias.domain.models.novelty.NoveltyModelimport com.matias.domain.models.offer.ProductOfferModelinterface MainActivityContract {	interface View : BaseContract.View {		fun showLoadingScreen()		fun showErrorScreen()		fun showInfoScreen()		fun changeStatusBarColor(toColor: Int)		fun initView()		fun updateView()	}	interface Presenter<V : View> : BaseContract.Presenter<V> {		fun getMainPosition()		fun getNoveltyList(): List<NoveltyModel>		fun getProductOfferList(): ArrayList<ProductOfferModel>	}}