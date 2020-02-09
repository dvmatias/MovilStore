package com.matias.components.tablayout.maintabview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.matias.components.R
import kotlinx.android.synthetic.main.styling_main_tab_custom_view.view.*

// TODO Refactor and code clena up
class StylingMainTabCustomView : LinearLayout {

	internal var labeled: Boolean = false

	internal var colorPrimary: Int? = null
		set(value) {
			field = value ?: R.color.color_main_tab_primary_default
			tvLabel.setTextColor(ContextCompat.getColor(context, field!!))
		}

	internal var colorSecondary: Int? = null
		set(value) {
			field = value ?: R.color.color_main_tab_secondary_default
		}

	internal var state: StylingMainTabCustomViewState = StylingMainTabCustomViewState.UNSELECTED
		set(value) {
			field = value
			when (field) {
				StylingMainTabCustomViewState.SELECTED -> setSelected()
				StylingMainTabCustomViewState.UNSELECTED -> setUnselected()
			}
		}

	internal var icon: Int = -1
		set(value) {
			field = value
			ivTabIcon.setImageDrawable(ContextCompat.getDrawable(context, field))
		}

	internal var text: String? = null
		set(value) {
			field = value
			tvLabel.text = field
		}

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
		View.inflate(context, R.layout.styling_main_tab_custom_view, this)

		colorPrimary ?: R.color.color_main_tab_primary_default
		colorSecondary ?: R.color.color_main_tab_secondary_default
	}

	/**
	 * Set tab in selected mode [StylingMainTabCustomViewState.SELECTED].
	 */
	private fun setSelected() {
		if (labeled) {
			tvLabel.alpha = 0f
			tvLabel.visibility = View.VISIBLE
			tvLabel.animate().setDuration(700).alpha(1.0f)

			separatorStart.visibility = View.VISIBLE
			separatorEnd.visibility = View.VISIBLE
		}

		val colorFade = ObjectAnimator.ofObject(
			cv,
			"cardBackgroundColor",
			ArgbEvaluator(),
			Color.TRANSPARENT,
			ColorUtils.setAlphaComponent(ContextCompat.getColor(context, colorPrimary!!), 30)
		)
		colorFade.duration = 400
		colorFade.start()

		val drawable = ContextCompat.getDrawable(context, icon)
		drawable?.let { d: Drawable ->
			val wrappedDrawable: Drawable? = DrawableCompat.wrap(d)
			wrappedDrawable?.let { wd: Drawable ->
				DrawableCompat.setTint(wd, ContextCompat.getColor(context, colorPrimary!!))
				ivTabIcon.setImageDrawable(wd)
			}
		}

	}

	/**
	 * Set tab in unslected mode [StylingMainTabCustomViewState.UNSELECTED].
	 */
	private fun setUnselected() {
		tvLabel.alpha = 1f
		tvLabel.animate().setDuration(200).alpha(0.0f)
		tvLabel.visibility = View.GONE

		separatorStart.visibility = View.GONE
		separatorEnd.visibility = View.GONE

		val colorFade = ObjectAnimator.ofObject(
			cv,
			"cardBackgroundColor",
			ArgbEvaluator(),
			ColorUtils.setAlphaComponent(ContextCompat.getColor(context, colorPrimary!!), 30),
			Color.TRANSPARENT
		)
		colorFade.duration = 400
		colorFade.start()

		val drawable = ContextCompat.getDrawable(context, icon)
		drawable?.let { d: Drawable ->
			val wrappedDrawable: Drawable? = DrawableCompat.wrap(d)
			wrappedDrawable?.let { wd: Drawable ->
				DrawableCompat.setTint(wd, ContextCompat.getColor(context, colorSecondary!!))
				ivTabIcon.setImageDrawable(wd)
			}
		}
	}

}