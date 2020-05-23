package com.matias.features.login.di.fragments.signin

import com.matias.core.base.diap.base.ViewComponent
import com.matias.features.login.ui.fragments.signin.SignInFragment
import dagger.Subcomponent

/**
 * Dagger Component
 *
 * @see [SignInFragment] client
 * @see [SignInFragmentModule] module
 */
@Subcomponent(modules = [(SignInFragmentModule::class)])
interface SignInFragmentSubcomponent : ViewComponent<SignInFragment>