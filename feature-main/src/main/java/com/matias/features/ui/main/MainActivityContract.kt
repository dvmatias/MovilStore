package com.matias.features.ui.mainimport com.matias.core.base.mvp.BaseContractinterface MainActivityContract {	interface View : BaseContract.View {		fun changeStatusBarColor(toColor: Int)		fun onToolbarOptionButtonClick(id: Int)	}	interface Presenter<V : View> : BaseContract.Presenter<V>}