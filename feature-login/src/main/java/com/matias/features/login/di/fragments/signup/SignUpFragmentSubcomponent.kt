package com.matias.features.login.di.fragments.signup

import com.matias.core.base.diap.base.ViewComponent
import com.matias.features.login.ui.fragments.signup.SignUpFragment
import dagger.Subcomponent

/**
 * Dagger Component
 *
 * @see [SignUpFragment] client
 * @see [SignUpFragmentModule] module
 */
@Subcomponent(modules = [(SignUpFragmentModule::class)])
interface SignUpFragmentSubcomponent : ViewComponent<SignUpFragment>