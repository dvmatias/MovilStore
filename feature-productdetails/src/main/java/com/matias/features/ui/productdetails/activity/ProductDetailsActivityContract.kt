package com.matias.features.ui.productdetails.activity

import com.matias.core.base.mvp.BaseContract
import com.matias.domain.models.product.ProductModel

interface ProductDetailsActivityContract {

	interface View : BaseContract.View {

		fun showLoadingScreen()
		fun showErrorScreen()
		fun showInfoScreen()
		fun setProductInfo(product: ProductModel, isNewProduct: Boolean, isOfferProduct: Boolean, isFeaturedProduct: Boolean)

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun getProduct(productId: Int)

	}

}