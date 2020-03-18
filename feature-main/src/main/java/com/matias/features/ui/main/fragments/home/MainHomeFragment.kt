package com.matias.features.ui.main.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matias.core.base.mvp.BasePresenterFragment
import com.matias.features.R
import com.matias.features.di.main.fragments.home.MainHomeFragmentModule
import com.matias.features.di.main.fragments.home.MainHomeFragmentSubcomponent
import com.matias.features.ui.MainUiComponent

class MainHomeFragment :
	BasePresenterFragment<
			MainHomeFragment,
			MainHomeFragmentPresenter,
			MainHomeFragmentSubcomponent>(),
	MainHomeFragmentContract.View {

	companion object {

		@JvmStatic
		fun newInstance(): MainHomeFragment =
			MainHomeFragment().apply {
				arguments = Bundle().apply { }
			}
	}

	override fun bindComponent(): MainHomeFragmentSubcomponent =
		MainUiComponent.component.plus(MainHomeFragmentModule())

	override fun bindLayout(): Int =
		R.layout.fragment_main_home

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let { }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
		inflater.inflate(R.layout.fragment_main_home, container, false)

}
