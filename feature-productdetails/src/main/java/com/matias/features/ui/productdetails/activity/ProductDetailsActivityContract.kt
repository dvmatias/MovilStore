package com.matias.features.ui.productdetails.activity

import com.matias.core.base.mvp.BaseContract
import com.matias.domain.models.product.ProductModel

interface ProductDetailsActivityContract {

	interface View : BaseContract.View {

		fun showLoadingScreen()
		fun showErrorScreen()
		fun showInfoScreen()
		fun setProduct(product: ProductModel)
		fun setIsNew(isNew: Boolean)
		fun setIsOffer(isOffer: Boolean)
		fun setIsFeatured(isFeatured: Boolean)
		fun setProductInfo()
		fun onUserClickShareBtn()
		fun onUserClickFavoriteBtn()
		fun onUserClickViewAllCommentsBtn()
		fun onUserClickAddToCartButton()
		fun onUserClickBuyButton()

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun getProduct(productId: Int)

	}

}