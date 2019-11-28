package com.matias.core.base.mvp

import android.os.Bundle
import android.view.View
import com.matias.core.base.diap.base.ViewComponent
import javax.inject.Inject

abstract class BasePresenterFragment<
		in V : BaseContract.View,
		P : BaseContract.Presenter<V>,
		out C : ViewComponent<V>> :
	BaseFragment<V, C>() {

	@Inject
	protected lateinit var presenter: P

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		@Suppress("UNCHECKED_CAST")
		presenter.attachView(this as V)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		presenter.create()
	}

	override fun onResume() {
		super.onResume()
		presenter.resume()
	}

	override fun onPause() {
		super.onPause()
		presenter.pause()
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.destroy()
	}

}