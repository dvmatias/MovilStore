package com.matias.features.ui.main

import android.animation.ObjectAnimator
import android.view.View

private const val DELAY_MULTIPLIER = 350L
private const val ANIM_DURATION = 750L

class MainScreenAnimator<V : View>(val a: List<V>) {

	fun animateIn() {
		for (view in a) {
			view.apply {
				alpha = 0f
				visibility = View.VISIBLE

				animate()
					.alpha(1f)
					.setDuration(ANIM_DURATION)
					.setListener(null)
					.startDelay = DELAY_MULTIPLIER * a.indexOf(view)

				translationY = -75f
				ObjectAnimator.ofFloat(view, "translationY", 0f).apply {
					duration = ANIM_DURATION
					startDelay = DELAY_MULTIPLIER * a.indexOf(view)
					start()
				}
			}
		}
	}

}