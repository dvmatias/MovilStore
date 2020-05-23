package com.matias.core.helpers

import android.animation.ObjectAnimator
import android.view.View

fun objectTranslationYAnim(target: View, fromPx: Float, toPx: Float, duration: Long) {
	target.translationY = fromPx
	ObjectAnimator.ofFloat(target, "translationY", toPx).apply {
		this.duration = duration
		this.start()
	}
}

fun objectAlphaAnimIn(target: View, from: Float, to: Float, duration: Long) {
	target.alpha = from
	target.visibility = View.VISIBLE
	ObjectAnimator.ofFloat(target, "alpha", to).apply {
		this.duration = duration
		this.start()
	}
}