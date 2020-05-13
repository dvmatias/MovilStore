package com.matias.features.ui.main.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matias.components.viewpager.StylingWrapContentHeightViewPager
import com.matias.core.base.mvp.BasePresenterFragment
import com.matias.core.helpers.dpToPx
import com.matias.features.R
import com.matias.features.di.main.fragments.home.MainHomeFragmentModule
import com.matias.features.di.main.fragments.home.MainHomeFragmentSubcomponent
import com.matias.features.ui.MainUiComponent
import com.matias.features.ui.main.fragments.home.novelty.NoveltyPagerAdapter
import javax.inject.Inject

class MainHomeFragment :
	BasePresenterFragment<
			MainHomeFragment,
			MainHomeFragmentPresenter,
			MainHomeFragmentSubcomponent>(),
	MainHomeFragmentContract.View {

	// Views.
	private lateinit var sectionNovelty: ViewGroup
	private lateinit var pagerNovelty: StylingWrapContentHeightViewPager

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
		setViews()
		setupNoveltyPager()
	}

	/**
	 *
	 */
	private fun setViews() {
		view?.let {v: View ->
			sectionNovelty = v.findViewById(R.id.section_novelty)
			pagerNovelty = sectionNovelty.findViewById(R.id.pager_novelty)
		}
	}

	/**
	 *
	 */
	private fun setupNoveltyPager() {
		noveltyPagerAdapter.setData(presenter.getNoveltyList())
		pagerNovelty.apply {
			adapter = noveltyPagerAdapter
			setCurrentItem(noveltyPagerAdapter.count / 2, false)
			offscreenPageLimit = 2
			clipToPadding = false
			activity?.let {
				this.setPadding(dpToPx(it, 12), 0, dpToPx(it, 12), 0)
//				this.pageMargin = dpToPx(it, 4)
			}
		}

	}

}