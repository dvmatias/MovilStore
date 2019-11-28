package com.matias.core.base.mvp

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.usecase.UseCaseContract

abstract class BasePresenter<V : BaseContract.View>(
	private vararg val useCaseList: UseCaseContract
) : BaseContract.Presenter<V> {

	protected var view: V? = null

	override fun attachView(view: V) {
		this.view = view
	}

	override fun destroy() {
		for (useCase in useCaseList) {
			useCase.cancel()
		}
		this.view = null
	}

	protected open fun handleError(error: FailureType) {}

}