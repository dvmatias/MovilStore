package com.matias.components.toolbar.toolbarmain

import android.content.Context
import android.graphics.drawable.VectorDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatDrawableManager
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.matias.components.R
import com.matias.components.toolbar.toolbarmain.StylingToolbarMainMode.LOADING
import com.matias.components.toolbar.toolbarmain.StylingToolbarMainMode.NORMAL
import com.matias.core.helpers.VectorDrawableHelper
import kotlinx.android.synthetic.main.styling_toolbar_main.view.*

class StylingToolbarMain : ConstraintLayout, StylingToolbarMainContract {

	private var listenerAdapter: InternalListener = InternalListener()

	private var oldMode: StylingToolbarMainMode = LOADING

	private var setMode: StylingToolbarMainMode = LOADING

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
		setMode(LOADING)
	}

	fun animateIcons(vectorDrawableHelper: VectorDrawableHelper?, delay: Long) {
		animateNavigationIcon(vectorDrawableHelper, delay)
		animateAnimateCouponIcon(vectorDrawableHelper, delay)
		animateAnimateNotificationIcon(vectorDrawableHelper, delay)
	}

	private fun animateNavigationIcon(vectorDrawableHelper: VectorDrawableHelper?, delay: Long) {
		vectorDrawableHelper?.let {
			animateIcon(buttonNavigation, it, delay)
		}
	}

	private fun animateAnimateCouponIcon(vectorDrawableHelper: VectorDrawableHelper?, delay: Long) {
		vectorDrawableHelper?.let {
			animateIcon(buttonCoupon, it, delay)
		}
	}

	private fun animateAnimateNotificationIcon(vectorDrawableHelper: VectorDrawableHelper?, delay: Long) {
		vectorDrawableHelper?.let {
			animateIcon(buttonNotification, it, delay)
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
			LOADING -> {
				buttonNavigation.visibility = View.INVISIBLE
				buttonCoupon.visibility = View.INVISIBLE
				buttonNotification.visibility = View.INVISIBLE
			}
			// Toolbar already initialized.
			NORMAL -> {
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