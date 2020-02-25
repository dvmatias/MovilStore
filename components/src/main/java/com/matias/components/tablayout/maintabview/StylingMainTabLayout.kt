package com.matias.components.tablayout.maintabview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout.*
import com.matias.components.R
import kotlinx.android.synthetic.main.styling_main_tab_layout.view.*


class StylingMainTabLayout : FrameLayout {

	private var onTabSelectedListenerAdapter = InternalOnTabSelectedListener()

	private var _backgroundLayoutColor: Int = ContextCompat.getColor(context, R.color.colorWhite)

	private lateinit var tabUiModelList: List<SatylingMainTabItemUiModel>

	private var labeled: Boolean = false

	constructor(context: Context) : super(context) {
		init(context, null)
	}

	constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
		init(context, attributeSet)
	}

	constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
		init(context, attributeSet)
	}

	/**
	 * Init view.
	 */
	@Suppress("UNUSED_PARAMETER")
	private fun init(context: Context, attributeSet: AttributeSet?) {
		View.inflate(context, R.layout.styling_main_tab_layout, this)

		val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.StylingMainTabLayout, 0, 0)
		try {
			val indexCount = typedArray.indexCount
			for (i in 0 until indexCount) {
				when (typedArray.getIndex(i)) {
					R.styleable.StylingMainTabLayout_backgroundLayoutColor ->
						_backgroundLayoutColor = typedArray.getColor(
							R.styleable.StylingMainTabLayout_backgroundLayoutColor, -1
						)
				}
			}
		} catch (e: Exception) {
			e.printStackTrace()
		} finally {
			setBackgroundColor(_backgroundLayoutColor)

			tabLayout.isInlineLabel = true
			tabLayout.tabRippleColor = null

			typedArray.recycle()
		}
	}

	/**
	 * Add tab selected listener.
	 */
	fun addOnTabSelectedListener(listener: OnTabSelectedListener?) {
		listener?.let {
			onTabSelectedListenerAdapter.setListener(it)
			tabLayout.addOnTabSelectedListener(onTabSelectedListenerAdapter)
		}
	}

	/**
	 * Bind view pager to Tab layout and prepare the tabs custom views.
	 */
	fun setup(viewPager: ViewPager?, autoRefresh: Boolean, tabUiModelList: List<SatylingMainTabItemUiModel>, labeled: Boolean) {
		tabLayout.setupWithViewPager(viewPager, autoRefresh)
		this.tabUiModelList = tabUiModelList
		this.labeled = labeled

		if (!labeled) {
			tabLayout.tabMode = MODE_FIXED
			tabLayout.tabGravity = GRAVITY_FILL
		} else {
			tabLayout.tabMode = MODE_SCROLLABLE
			tabLayout.tabGravity = GRAVITY_CENTER
		}

		addCustomTabs()
		removePagerTabs()
	}

	/**
	 * Add a [StylingMainTabCustomView] view for each tab.
	 */
	private fun addCustomTabs() {
		for (i in 0 until tabUiModelList.size) {
			val tab = tabLayout.newTab()
			tab.customView = createCustonTextView(tabUiModelList[i])
			tabLayout.addTab(tab)
			(tab.customView as StylingMainTabCustomView).state = when (i) {
				0 -> StylingMainTabCustomViewState.SELECTED
				else -> StylingMainTabCustomViewState.UNSELECTED
			}
		}
	}

	/**
	 * Create a [StylingMainTabCustomView] view for each tab.
	 */
	private fun createCustonTextView(tabUiModel: SatylingMainTabItemUiModel): StylingMainTabCustomView =
		StylingMainTabCustomView(context).apply {
			labeled = this@StylingMainTabLayout.labeled
			icon = tabUiModel.iconDrawableId
			text = tabUiModel.label
			colorPrimary = tabUiModel.colorPrimary
			colorSecondary = tabUiModel.colorSecondary
		}

	/**
	 * Remove tabs added for pager, let only custom.
	 */
	private fun removePagerTabs() {
		val times = tabLayout.tabCount - tabUiModelList.size
		repeat(times) {
			tabLayout.removeTabAt(0)
		}
	}

	internal class InternalOnTabSelectedListener: OnTabSelectedListener {

		private var listener: OnTabSelectedListener? = null

		fun setListener(listener: OnTabSelectedListener) {
			this.listener = listener
		}

		override fun onTabReselected(tab: Tab?) {
			listener?.onTabReselected(tab)
		}

		override fun onTabUnselected(tab: Tab?) {
			listener?.onTabUnselected(tab)

			val customView = tab?.customView
			if (customView is StylingMainTabCustomView) {
				customView.state = StylingMainTabCustomViewState.UNSELECTED
			}
		}

		override fun onTabSelected(tab: Tab?) {
			listener?.onTabSelected(tab)

			val customView = tab?.customView
			if (customView is StylingMainTabCustomView) {
				customView.state = StylingMainTabCustomViewState.SELECTED
			}
		}
	}

}