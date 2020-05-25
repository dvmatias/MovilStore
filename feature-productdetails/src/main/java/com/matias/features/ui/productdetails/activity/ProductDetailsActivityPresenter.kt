package com.matias.features.ui.productdetails.activity

import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.product.ProductModel
import com.matias.domain.usecases.product.GetProductDetailUseCase
import javax.inject.Inject

class ProductDetailsActivityPresenter @Inject constructor(
	private val getProductDetailUseCase: GetProductDetailUseCase
) : BasePresenter<ProductDetailsActivityContract.View>(),
	ProductDetailsActivityContract.Presenter<ProductDetailsActivityContract.View>{

	/**
	 * [ProductDetailsActivityContract.Presenter] implementation.
	 */
	override fun getProduct(productId: Int) {
		view?.let { view ->
			view.showLoadingScreen()
			getProductDetailUseCase(
				{ it.either(::onGetProductDetailFailure, ::onGetProductDetailSucces) },
				GetProductDetailUseCase.Params(productId)
			)
		}
	}

	private fun onGetProductDetailSucces(product: ProductModel) {
		view?.let {
			it.setProductInfo(product)
			it.showInfoScreen()
		}
	}

	private fun onGetProductDetailFailure(e: FailureType) {

	}
}