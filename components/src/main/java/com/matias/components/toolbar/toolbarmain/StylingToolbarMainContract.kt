package com.matias.components.toolbar.toolbarmain

import android.view.View

interface StylingToolbarMainContract {

	fun setOnCLickListener(listener: View.OnClickListener?)

	fun setMode(mode: StylingToolbarMainMode)

}