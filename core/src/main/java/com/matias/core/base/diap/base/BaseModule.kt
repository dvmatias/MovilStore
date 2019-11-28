package com.matias.core.base.diap.base

import android.app.Application
import android.content.Context
import com.matias.core.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule(private val app: BaseApplication) {

	@Provides
	@Singleton
	fun provideApplication(): Application = app

	@Provides
	@Singleton
	fun provideContext(): Context = app

}