package com.matias.features.ui.main.activity

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.matias.core.helpers.dpToPx

private const val DIVIDER_MARGIN_START: Float = 76F
private const val DIVIDER_HEIGHT: Float = 0.7F

class ItemServiceDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

	private var divider: Drawable? = null

	init {
		val a = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
		divider = a.getDrawable(0)
		if (divider == null) {
			Log.w(
				ItemServiceDecoration::class.java.simpleName, "@android:attr/listDivider was not set in the theme used for this "
						+ "DividerItemDecoration. Please set that attribute all call setDrawable()"
			)
		}
		a.recycle()
	}

	override fun onDraw(c: Canvas, parent: RecyclerView) {
		if (parent.layoutManager == null) {
			return
		}

		c.save()
		val left = parent.paddingLeft + dpToPx(context, DIVIDER_MARGIN_START)
		val right = parent.width
		val childCount = parent.childCount

		for (i in 0 until childCount) {
			val child = parent.getChildAt(i)
			val params = child.layoutParams as RecyclerView.LayoutParams
			val top = child.bottom + params.bottomMargin
			val bottom = top + dpToPx(context, DIVIDER_HEIGHT)

			divider?.apply {
				setBounds(left.toInt(), top, right, bottom.toInt())
				draw(c)
			}

		}
		c.restore()
	}

}