package com.matias.core.base.diap.helpers

import android.content.Context
import com.matias.core.helpers.VectorDrawableHelper
import dagger.Module
import dagger.Provides

@Module
class HelpersModule {

	@Provides
	fun provideVectorDrawableHelper(context: Context): VectorDrawableHelper =
		VectorDrawableHelper(context)

}