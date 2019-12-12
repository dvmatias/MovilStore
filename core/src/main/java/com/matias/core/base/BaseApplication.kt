package com.matias.core.base

import android.app.Application
import com.matias.core.base.diap.base.BaseComponent
import com.matias.core.base.diap.base.BaseModule
import com.matias.core.base.diap.base.DaggerBaseComponent
import com.matias.core.navigator.Navigator

abstract class BaseApplication : Application() {
    
    companion object {
        internal lateinit var navigator: Navigator
        lateinit var baseComponent: BaseComponent
    }
    
    abstract fun bindNavigator(): Navigator
    
    override fun onCreate() {
        super.onCreate()
        initInjector()
        initNavigator()
    }
    
    private fun initInjector() {
        baseComponent = DaggerBaseComponent
                .builder()
                .baseModule(BaseModule(this))
                .build()
    }
    
    private fun initNavigator() {
        navigator = bindNavigator()
    }
    
}