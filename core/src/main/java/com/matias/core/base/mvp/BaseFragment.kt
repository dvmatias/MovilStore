package com.matias.core.base.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matias.core.base.diap.base.ViewComponent

const val ATTACH_TO_ROOT_FALSE: Boolean = false

abstract class BaseFragment<
		in V : BaseContract.View,
		out C : ViewComponent<V>> : Fragment() {

	protected abstract fun bindComponent(): C

	protected abstract fun bindLayout(): Int

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		@Suppress("UNCHECKED_CAST")
		bindComponent().inject(this as V)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(bindLayout(), container, ATTACH_TO_ROOT_FALSE)
	}

}