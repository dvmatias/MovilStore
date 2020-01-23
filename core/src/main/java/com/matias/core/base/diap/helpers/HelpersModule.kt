package com.matias.core.base.diap.helpers

import android.content.Context
import com.matias.core.helpers.HtmlHelper
import dagger.Module
import dagger.Provides

@Module
class HelpersModule {

	@Provides
	fun provideHtmlHelper(context: Context): HtmlHelper = HtmlHelper(context)

}