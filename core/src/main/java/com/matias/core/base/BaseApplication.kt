package com.matias.core.base

import android.app.Application
import com.matias.core.base.diap.base.BaseComponent
import com.matias.core.base.diap.base.BaseModule
import com.matias.core.base.diap.base.DaggerBaseComponent

abstract class BaseApplication : Application() {

	companion object {
		lateinit var baseComponent: BaseComponent
	}

	override fun onCreate() {
		super.onCreate()
		initInjector()
	}

	private fun initInjector() {
		baseComponent = DaggerBaseComponent
			.builder()
			.baseModule(BaseModule(this))
			.build()
	}

}