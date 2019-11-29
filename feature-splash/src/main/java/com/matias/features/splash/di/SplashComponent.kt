package com.matias.features.splash.di

import com.matias.core.base.diap.base.BaseComponent
import com.matias.features.splash.di.splash.SplashActivityComponent
import com.matias.features.splash.di.splash.SplashActivityModule
import dagger.Component

@Component(dependencies = [(BaseComponent::class)], modules = [(SplashModule::class)])
@SplashScope
interface SplashComponent {

	fun plus(splashActivityModule: SplashActivityModule): SplashActivityComponent

}