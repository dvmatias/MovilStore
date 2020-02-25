package com.matias.components.toolbar.toolbarmain

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.matias.components.R
import com.matias.core.helpers.VectorDrawableHelper
import kotlinx.android.synthetic.main.styling_toolbar_main.view.*
import kotlinx.android.synthetic.main.styling_toolbar_main_footer_to_search.view.*


class StylingToolbarMain : ConstraintLayout, StylingToolbarMainContract {

	private var listenerAdapter: InternalListener = InternalListener()

	constructor(context: Context) : super(context) {
		init(context)
	}

	constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
		init(context)
	}

	constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
		init(context)
	}

	private fun init(context: Context) {
		View.inflate(context, R.layout.styling_toolbar_main, this)

		setMode(StylingToolbarMainMode.TO_SEARCH)
	}

	private fun transitionFooter(mode: StylingToolbarMainMode) {
		val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		mode.footerLayoutId?.let {
			val footerView = inflater.inflate(it, null) as ConstraintLayout
			footerContainer.addView(footerView, 0)
		}
	}

	fun animateIcons(vectorDrawableHelper: VectorDrawableHelper?) {
		vectorDrawableHelper?.run {
			startVectorAnimationWithDelay(buttonNotification, 500)
			startVectorAnimationWithDelay(buttonNavigation, 500)
		}
	}

	/**
	 * Sets the listener object that is triggered when the view is clicked.
	 *
	 * @param listener The instance of the listener to trigger.
	 */
	override fun setOnCLickListener(listener: OnClickListener?) {
		listener?.let {
			listenerAdapter.setListener(it)

			buttonNavigation.setOnClickListener(listenerAdapter)
			buttonNotification.setOnClickListener(listenerAdapter)
			buttonSearch.setOnClickListener(listenerAdapter)
			iconFilterSearchButton.setOnClickListener(listenerAdapter)
			labelFilterSearchButton.setOnClickListener(listenerAdapter)
		}
	}

	/**
	 * Set the toolbar mode
	 *
	 * @param mode The mode [StylingToolbarMainMode] to set the toolbar
	 */
	override fun setMode(mode: StylingToolbarMainMode) {
		transitionFooter(mode)
	}

	/**
	 * Internal click listener class. Translates a view’s click listener to
	 * one that is more appropriate for this custom button class.
	 */
	inner class InternalListener : OnClickListener {

		private var listener: OnClickListener? = null

		override fun onClick(view: View?) {
			listener?.onClick(view)
		}

		fun setListener(listener: OnClickListener) {
			this.listener = listener
		}
	}

}