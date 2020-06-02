package com.matias.features.ui.main

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup

private const val DELAY_START = 350L
private const val DELAY_MULTIPLIER = 350L
private const val ANIM_DURATION = 750L

class MainScreenAnimatorImpl: MainScreenAnimator() {


	override fun setViewGropu(viewGroup: ViewGroup) {
		super.viewGroup = viewGroup
		prepareChilds()
	}

	/**
	 * Only add childrens in which the visibility status is different than View.GONE.
	 */
	private fun prepareChilds() {
		super.viewGroup?.let { viewGroup: ViewGroup ->
			val childCount = viewGroup.childCount

			for (i in 0 until childCount) {
				viewGroup.getChildAt(i)?.let {
					if (it.visibility != View.GONE) {
						super.childs.add(it)
						it.visibility = View.INVISIBLE
					}
				}
			}
		}
	}

	override fun animateViewsIn() {
		for (child in super.childs) {
			child.apply {
				alpha = 0f
				visibility = View.VISIBLE

				val delay = getStartDelay(super.viewGroup?.indexOfChild(child))

				animate()
					.alpha(1f)
					.setDuration(ANIM_DURATION)
					.setListener(null)
					.startDelay = delay

				translationY = -75f
				ObjectAnimator.ofFloat(child, "translationY", 0f).apply {
					duration = ANIM_DURATION
					startDelay = delay
					start()
				}
			}
		}

	}

	private fun getStartDelay(viewIndex: Int?): Long =
		viewIndex?.let { DELAY_START + (DELAY_MULTIPLIER * it) } ?: 0

}