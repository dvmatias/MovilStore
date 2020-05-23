package com.matias.features.ui.main.decorations


import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.matias.core.helpers.dpToPx

private const val FIRST_POSITION: Int = 0
private const val OFFEST_START: Float = 20F
private const val OFFEST_END: Float = OFFEST_START
private const val OFFEST_INTER: Float = 8F

class ItemCategoryDecoration : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
		if (parent.layoutManager == null || parent.adapter == null) {
			return
		}

		val itemCount = parent.adapter!!.itemCount
		val childPosition = parent.getChildAdapterPosition(view)
		// First item
		if (childPosition == FIRST_POSITION) {
			outRect.left = dpToPx(parent.context, OFFEST_START).toInt()
		} else {
			outRect.left = dpToPx(parent.context, OFFEST_INTER).toInt()
		}
		// Last item
		if ( childPosition + 1 == itemCount) {
			outRect.right = dpToPx(parent.context, OFFEST_END).toInt()
		}
	}

}