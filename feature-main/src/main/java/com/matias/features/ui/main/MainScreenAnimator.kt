package com.matias.features.ui.main

import android.view.View
import android.view.ViewGroup

abstract class MainScreenAnimator {

	protected var viewGroup: ViewGroup? = null
	protected var childs: ArrayList<View> = arrayListOf()

	abstract fun setViewGropu(viewGroup: ViewGroup)

	abstract fun animateViewsIn()

}