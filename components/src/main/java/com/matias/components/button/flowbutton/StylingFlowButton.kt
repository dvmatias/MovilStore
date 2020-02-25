package com.matias.components.button.flowbutton

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.matias.components.R
import kotlinx.android.synthetic.main.flow_button_styling.view.*

private const val BACKGROUND_ENABLED_ALPHA = 1.0F
private const val ICON_ENABLED_ALPHA = 0.7F
private const val DISABLED_ALPHA = 0.2F
class StylingFlowButton : CardView {

	private val listenerAdapter = InternalListener()

	private var _buttonColor: Int? =
		ContextCompat.getColor(context, R.color.colorFlowButtonBackgroundDefault)

	private var _iconColor: Int? =
		ContextCompat.getColor(context, R.color.colorFlowButtonIconDefault)

	private var _buttonState: Int? = StylingFlowButtonState.STATE_DISABLED.state

	/**
	 * Button background color.
	 */
	private var buttonColor: Int?
		get() = _buttonColor
		set(value) {
			_buttonColor = value
			cardButton.setCardBackgroundColor(_buttonColor!!)
		}

	/**
	 * Icon tint color.
	 */
	private var iconColor: Int?
		get() = _iconColor
		set(value) {
			_iconColor = value
		}

	/**
	 * Button style (filled/outlined).
	 */
	var buttonState: Int?
		get() = _buttonState
		set(value) {
			_buttonState = value
			when (_buttonState) {
				StylingFlowButtonState.STATE_ENABLED.state -> setEnabledStatus()
				StylingFlowButtonState.STATE_DISABLED.state -> setDisabledStatus()
				StylingFlowButtonState.STATE_LOADING.state -> setLoadingStatus()
				StylingFlowButtonState.STATE_OK.state -> setOkStatus()
			}
		}

	constructor(context: Context) : super(context) {
		init(context, null)
	}

	constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
		init(context, attributeSet)
	}

	constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
		init(context, attributeSet)
	}

	private fun init(context: Context, attributeSet: AttributeSet?) {
		View.inflate(context, R.layout.flow_button_styling, this)
		// Remove window color.
		setBackgroundColor(Color.TRANSPARENT)

		val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.StylingFlowButton, 0, 0)
		try {
			val indexCount = typedArray.indexCount
			for (i in 0 until indexCount) {
				when (typedArray.getIndex(i)) {
					R.styleable.StylingFlowButton_buttonColor ->
						_buttonColor = typedArray.getColor(
							R.styleable.StylingFlowButton_buttonColor, _buttonColor!!
						)
					R.styleable.StylingFlowButton_iconColor ->
						_iconColor = typedArray.getColor(
							R.styleable.StylingFlowButton_iconColor, _iconColor!!
						)
					R.styleable.StylingFlowButton_buttonStatus -> {
						_buttonState = typedArray.getInt(
							R.styleable.StylingFlowButton_buttonStatus,
							StylingFlowButtonState.STATE_DISABLED.state
						)
					}
				}
			}
		} catch (e: Exception) {
			e.printStackTrace()
		} finally {
			buttonColor = _buttonColor
			iconColor = _iconColor
			buttonState = _buttonState

			setOnClickListener(listenerAdapter)

			typedArray.recycle()
		}
	}

	private fun setEnabledStatus() {
		// Background
		cardButton.alpha = BACKGROUND_ENABLED_ALPHA
		// Icon
		ivStatus.alpha = ICON_ENABLED_ALPHA
		ivStatus.setImageResource(R.drawable.ic_flow_button_next_vector)
		val wrappedDrawable = DrawableCompat.wrap(ivStatus.drawable)
		DrawableCompat.setTint(wrappedDrawable, _iconColor!!)
		ivStatus.setImageDrawable(wrappedDrawable)
	}

	private fun setDisabledStatus() {
		// Background
		cardButton.alpha = DISABLED_ALPHA
		// Icon
		ivStatus.alpha = DISABLED_ALPHA
		ivStatus.setImageResource(R.drawable.ic_flow_button_next_vector)
		val wrappedDrawable = DrawableCompat.wrap(ivStatus.drawable)
		DrawableCompat.setTint(wrappedDrawable, _iconColor!!)
		ivStatus.setImageDrawable(wrappedDrawable)
	}

	private fun setLoadingStatus() {
		// Background
		cardButton.alpha = BACKGROUND_ENABLED_ALPHA
		// Icon
		ivStatus.alpha = ICON_ENABLED_ALPHA
		ivStatus.setImageResource(R.drawable.ic_flow_button_loading_vector)
		val wrappedDrawable = DrawableCompat.wrap(ivStatus.drawable)
		DrawableCompat.setTint(wrappedDrawable, _iconColor!!)
		ivStatus.setImageDrawable(wrappedDrawable)
		val d = ivStatus.drawable
		if (d is AnimatedVectorDrawableCompat) d.start()
		else if (d is AnimatedVectorDrawable) d.start()
	}

	private fun setOkStatus() {
		// Background
		cardButton.let {
			it.alpha = BACKGROUND_ENABLED_ALPHA
			it.animate().scaleX(1.1f).scaleY(1.1f)
				.setDuration(100)
				.withEndAction {
					it.animate().scaleX(1f).scaleY(1f)
						.setInterpolator(AccelerateDecelerateInterpolator())
						.duration = 200
				}
		}
		// Icon
		ivStatus.apply {
			alpha = ICON_ENABLED_ALPHA
			setImageResource(R.drawable.ic_flow_button_ok_vector)
			DrawableCompat.setTint(DrawableCompat.wrap(drawable), _iconColor!!)
			setImageDrawable(DrawableCompat.wrap(drawable))
			drawable.let { drawable: Drawable ->
				if (drawable is AnimatedVectorDrawableCompat) drawable.start()
				else if (drawable is AnimatedVectorDrawable) drawable.start()
			}
		}
	}

	/**
	 * Sets the listener object that is triggered when the view is clicked.
	 *
	 * @param l The instance of the listener to trigger.
	 */
	override fun setOnClickListener(l: OnClickListener?) {
		l?.let {
			listenerAdapter.setListener(it)
			cardButton.setOnClickListener(listenerAdapter)
		}
	}

	/**
	 * Internal click listener class. Translates a view’s click listener to
	 * one that is more appropriate for this custom button class.
	 */
	inner class InternalListener : OnClickListener {
		private var listener: OnClickListener? = null

		override fun onClick(v: View?) {
			when (buttonState) {
                StylingFlowButtonState.STATE_ENABLED.state -> listener?.onClick(this@StylingFlowButton)
                else -> {  }
            }
		}

		fun setListener(clickListener: OnClickListener) {
			listener = clickListener
		}
	}

}