package com.matias.features.di.mainimport com.matias.core.base.diap.base.ViewComponentimport com.matias.features.ui.main.activity.MainActivityimport dagger.Subcomponent@Subcomponent(modules = [(MainActivityModule::class)])interface MainActivitySubcomponent : ViewComponent<MainActivity>