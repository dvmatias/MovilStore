package com.matias.features.ui.productdetails

import com.matias.core.base.BaseApplication
import com.matias.features.di.DaggerProductDetailsComponent
import com.matias.features.di.ProductDetailsComponent
import com.matias.features.di.ProductDetailsModule

internal class ProductDetailsUiComponent {

	companion object {
		internal val component: ProductDetailsComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
			createComponent()
		}

		@Suppress("DEPRECATION")
		private fun createComponent(): ProductDetailsComponent =
			DaggerProductDetailsComponent.builder()
				.baseComponent(BaseApplication.baseComponent)
				.productDetailsModule(ProductDetailsModule())
				.build()
	}

}