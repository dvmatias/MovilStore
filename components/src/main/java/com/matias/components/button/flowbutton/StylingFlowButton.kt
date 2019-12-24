package com.matias.components.button.flowbutton

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.matias.components.R
import kotlinx.android.synthetic.main.flow_button_styling.view.*

class StylingFlowButton : CardView {

	companion object {
		private const val BACKGROUND_ENABLED_ALPHA = 1.0F
		private const val ICON_ENABLED_ALPHA = 0.7F
		private const val DISABLED_ALPHA = 0.2F
		private const val DELAY_ACTION_MS = 350L
	}

	private val listenerAdapter = InternalListener()

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

		try {
			// TODO Read custom attrs
		} catch (e: Exception) {
			e.printStackTrace()
		} finally {
			// TODO Set attrs and init things
			setOnClickListener(listenerAdapter)
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
			listener?.onClick(this@StylingFlowButton)
		}

		fun setListener(clickListener: OnClickListener) {
			listener = clickListener
		}
	}

}

