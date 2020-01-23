package com.matias.components.loading

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.matias.components.R
import kotlinx.android.synthetic.main.styling_loading_horizontal.view.*

class StylingLoadingHorizontal : FrameLayout {

	constructor(context: Context) : super(context) {
		init(context, null)
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		init(context, attrs)
	}

	constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
		context,
		attrs,
		defStyleAttr
	) {
		init(context, attrs)
	}

	@Suppress("UNUSED_PARAMETER")
	private fun init(context: Context, attrs: AttributeSet?) {
		View.inflate(context, R.layout.styling_loading_horizontal, this)


		val d = imageLoading.drawable
		if (d is AnimatedVectorDrawableCompat) d.start()
		else if (d is AnimatedVectorDrawable) d.start()
	}

}