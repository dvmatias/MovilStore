package com.matias.core.base.diap.helpers

import android.content.Context
import com.matias.core.helpers.HtmlHelper
import com.matias.core.helpers.VectorDrawableHelper
import dagger.Module
import dagger.Provides

@Module
class HelpersModule {

	@Provides
	fun provideHtmlHelper(context: Context): HtmlHelper =
		HtmlHelper(context)

	@Provides
	fun provideVectorDrawableHelper(context: Context): VectorDrawableHelper =
		VectorDrawableHelper(context)

}