package com.matias.features.ui.main.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.matias.core.helpers.dpToPx

private const val FIRST_POSITION: Int = 0
private const val OFFSET_START: Float = 20F
private const val OFFSET_END: Float = OFFSET_START
private const val OFFSET_INTER: Float = 2F

class ItemNewProductDecoration : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
		if (parent.layoutManager == null || parent.adapter == null) {
			return
		}

		val itemCount = parent.adapter!!.itemCount
		val childPosition = parent.getChildAdapterPosition(view)
		// First item
		if (FIRST_POSITION == childPosition) {
			outRect.left = dpToPx(parent.context, OFFSET_START).toInt()
		} else {
			outRect.left = dpToPx(parent.context, OFFSET_INTER).toInt()
		}
		// Last item
		if ( childPosition + 1 == itemCount) {
			outRect.right = dpToPx(parent.context, OFFSET_END).toInt()
		}
	}

}