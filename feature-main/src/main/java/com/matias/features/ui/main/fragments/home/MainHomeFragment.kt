package com.matias.features.ui.main.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matias.core.base.mvp.BasePresenterFragment
import com.matias.core.helpers.dpToPx
import com.matias.features.R
import com.matias.features.di.main.fragments.home.MainHomeFragmentModule
import com.matias.features.di.main.fragments.home.MainHomeFragmentSubcomponent
import com.matias.features.ui.MainUiComponent
import com.matias.features.ui.main.fragments.home.novelty.NoveltyPagerAdapter
import kotlinx.android.synthetic.main.section_novelty.*
import javax.inject.Inject

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

	@Inject
	lateinit var noveltyPagerAdapter: NoveltyPagerAdapter

	override fun bindComponent(): MainHomeFragmentSubcomponent =
		MainUiComponent.component.plus(MainHomeFragmentModule(this@MainHomeFragment))

	override fun bindLayout(): Int =
		R.layout.fragment_main_home

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let { }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
		inflater.inflate(R.layout.fragment_main_home, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupNoveltyPager()
	}

	/**
	 *
	 */
	private fun setupNoveltyPager() {
		noveltyPagerAdapter.setData(presenter.getNoveltyList())
		pagerNovelty.apply {
			adapter = noveltyPagerAdapter
			setCurrentItem(Int.MAX_VALUE / 2, false)
			offscreenPageLimit = 2
			clipToPadding = false
			activity?.let {
				this.setPadding(dpToPx(it, 16), 0, dpToPx(it, 16), 0)
				pagerNovelty.pageMargin = dpToPx(it, 6)
			}
		}

	}

}