package com.matias.core.base.helpers

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import java.lang.IllegalArgumentException

fun startVectorAnimation(view: ImageView, drawable: Drawable?, color: Int?) {
	drawable?.let {
		val wrappedDrawable = DrawableCompat.wrap(it)
		setDrawableTint(wrappedDrawable, color)
		animateDrawable(view, wrappedDrawable)
	} ?: throw IllegalArgumentException("Drawable can't be null")
}

fun startVectorAnimation(view: ImageView, color: Int) {
	val wrappedDrawable = DrawableCompat.wrap(view.drawable)
	setDrawableTint(wrappedDrawable, color)
	animateDrawable(view, wrappedDrawable)
}

fun startVectorAnimation(view: ImageView) {
	val wrappedDrawable = DrawableCompat.wrap(view.drawable)
	animateDrawable(view, wrappedDrawable)
}

private fun setDrawableTint(wrappedDrawable: Drawable?, color: Int?) {
	if (wrappedDrawable != null && color != null) {
		DrawableCompat.setTint(wrappedDrawable, color)
	}
}

private fun animateDrawable(view: ImageView, wrappedDrawable: Drawable?) {
	wrappedDrawable?.let { drawable: Drawable ->
		view.setImageDrawable(drawable)
		val d = view.drawable
		if (d is AnimatedVectorDrawableCompat) d.start()
		else if (d is AnimatedVectorDrawable) d.start()
	}

}