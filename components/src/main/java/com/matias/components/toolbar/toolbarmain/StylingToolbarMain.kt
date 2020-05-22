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
import kotlinx.android.synthetic.main.styling_toolbar_main.view.*

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
				buttonNavigation.visibility = View.VISIBLE
				buttonCoupon.visibility = View.VISIBLE
				buttonNotification.visibility = View.VISIBLE
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