package com.matias.components.toolbar.toolbarmain

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.matias.components.R
import com.matias.components.toolbar.toolbarmain.StylingToolbarMainMode.INIT
import com.matias.core.helpers.VectorDrawableHelper
import com.matias.core.helpers.dpToActualPx
import com.matias.core.helpers.objectAlphaAnimIn
import com.matias.core.helpers.objectTranslationYAnim
import kotlinx.android.synthetic.main.styling_toolbar_main.view.*
import kotlinx.android.synthetic.main.styling_toolbar_main_footer_to_search.view.*

class StylingToolbarMain : ConstraintLayout, StylingToolbarMainContract {

	private var listenerAdapter: InternalListener = InternalListener()

	private var oldMode: StylingToolbarMainMode = INIT

	private var setMode: StylingToolbarMainMode = INIT

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

		setMode(INIT)
	}

	private fun transitionFooter(mode: StylingToolbarMainMode) {
		val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		mode.footerLayoutId?.let {
			val footerView = inflater.inflate(it, null) as ConstraintLayout
			footerContainer.addView(footerView, 0)
		}
	}

	fun animateNavigationIcon(vectorDrawableHelper: VectorDrawableHelper?, delay: Long) {
		vectorDrawableHelper?.let {
			animateIcon(buttonNavigation, it, delay)
		}
	}

	private fun animateIcon(icon: ImageView, vectorDrawableHelper: VectorDrawableHelper, delay: Long) {
		vectorDrawableHelper.startVectorAnimationWithDelay(icon, delay)
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
			buttonCoupon.setOnClickListener(listenerAdapter)
			buttonNotification.setOnClickListener(listenerAdapter)

			buttonSearch?.setOnClickListener(listenerAdapter)
			iconFilterSearchButton?.setOnClickListener(listenerAdapter)
			labelFilterSearchButton?.setOnClickListener(listenerAdapter)
		}
	}

	private fun transitionNavigationIcon(mode: StylingToolbarMainMode) {
		// TODO
	}

	private fun transitionCouponIcon(mode: StylingToolbarMainMode) {
		// TODO
	}

	private fun transitionNotificationsIcon(mode: StylingToolbarMainMode) {
		// TODO
	}

	private fun initFooter(mode: StylingToolbarMainMode) {
		val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		mode.footerLayoutId?.let {
			val footerView = inflater.inflate(it, null) as ConstraintLayout
			footerContainer.apply {
				addView(footerView, 0)
				objectAlphaAnimIn(this, 0f, 1f, 450)
				objectTranslationYAnim(this, -dpToActualPx(context, 24), 0f, 450)
			}
		}
	}

	/**
	 * Set the toolbar mode
	 *
	 * @param newMode The mode [StylingToolbarMainMode] to set the toolbar
	 */
	override fun setMode(newMode: StylingToolbarMainMode) {
		when (newMode) {
			// Toolbar initialization, visible only brand name.
			INIT -> {
				buttonNavigation.visibility = View.INVISIBLE
				buttonCoupon.visibility = View.INVISIBLE
				buttonNotification.visibility = View.INVISIBLE
			}
			// Toolbar already initialized.
			else -> {
				// Came from init status
				if (INIT == oldMode) {
					buttonNavigation.visibility = View.VISIBLE
					buttonCoupon.visibility = View.VISIBLE
					buttonNotification.visibility = View.VISIBLE
					initFooter(newMode)
				} else {
					transitionNavigationIcon(newMode)
					transitionCouponIcon(newMode)
					transitionNotificationsIcon(newMode)
					transitionFooter(newMode)
				}
			}
		}
		oldMode = setMode
		this.setMode = newMode
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