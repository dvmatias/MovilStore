package com.matias.features.splash.di.splash

import com.matias.core.base.diap.base.ViewComponent
import com.matias.features.splash.ui.splash.SplashActivity
import dagger.Subcomponent

@Subcomponent(modules = [(SplashActivityModule::class)])
interface SplashActivitySubComponent : ViewComponent<SplashActivity> {
}