package com.matias.components.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

class StylingWrapContentHeightViewPager(context: Context, attributeSet: AttributeSet) : ViewPager(context, attributeSet) {

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		var height = 0
		var childHeight = 0

		for (i in 0 until childCount) {
			val child: View = getChildAt(i)
			child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
			childHeight = child.measuredHeight
		}

		if (childHeight > height) {
			height = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY)
		}

		super.onMeasure(widthMeasureSpec, height)
	}

}