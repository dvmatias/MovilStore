package com.matias.features.di

import com.matias.core.base.diap.base.BaseComponent
import com.matias.features.di.activity.ProductDetailsActivityModule
import com.matias.features.di.activity.ProductDetailsActivitySubcomponent
import dagger.Component

@Component(
	dependencies = [(BaseComponent::class)],
	modules = [(ProductDetailsModule::class)]
)
@ProductDetailsScope
internal interface ProductDetailsComponent {

	fun plus(target: ProductDetailsActivityModule): ProductDetailsActivitySubcomponent

}