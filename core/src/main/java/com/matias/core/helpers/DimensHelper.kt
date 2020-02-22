package com.matias.core.helpers

import android.content.Context
import android.util.TypedValue

fun dpToPx(context: Context, dp: Int): Int {
	val r = context.resources
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics).toInt()
}

fun dpToActualPx(context: Context, dp: Int): Float {
	val r = context.resources
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
}



fun pxToDp(context: Context, px: Int): Int {
	val r = context.resources
	return Math.round(px / (r.displayMetrics.densityDpi / 160f))
}

fun pxToActualDp(context: Context, px: Int): Float {
	val r = context.resources
	return px / (r.displayMetrics.densityDpi / 160f)
}