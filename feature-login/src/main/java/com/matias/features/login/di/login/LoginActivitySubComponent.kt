package com.matias.features.login.di.login

import com.matias.core.base.diap.base.ViewComponent
import com.matias.features.login.ui.login.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = [(LoginActivityModule::class)])
interface LoginActivitySubComponent : ViewComponent<LoginActivity>