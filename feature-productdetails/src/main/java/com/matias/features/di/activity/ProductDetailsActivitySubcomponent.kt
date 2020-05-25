package com.matias.features.di.activity

import com.matias.core.base.diap.base.ViewComponent
import com.matias.features.ui.productdetails.activity.ProductDetailsActivity
import dagger.Subcomponent

@Subcomponent(modules = [(ProductDetailsActivityModule::class)])
interface ProductDetailsActivitySubcomponent : ViewComponent<ProductDetailsActivity>