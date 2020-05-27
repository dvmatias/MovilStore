package com.matias.features.ui.productdetails.activity

import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.functional.Either
import com.matias.domain.base.functional.value
import com.matias.domain.models.product.ProductModel
import com.matias.domain.usecases.product.GetProductDetailUseCase
import com.matias.domain.usecases.product.IsFeaturedProductUseCase
import com.matias.domain.usecases.product.IsNewProductUseCase
import com.matias.domain.usecases.product.IsOfferProductUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

class ProductDetailsActivityPresenter @Inject constructor(
	private val getProductDetailUseCase: GetProductDetailUseCase,
	private val isOfferProductUseCase: IsOfferProductUseCase,
	private val isNewProductUseCase: IsNewProductUseCase,
	private val isFeaturedProductUseCase: IsFeaturedProductUseCase
) : BasePresenter<ProductDetailsActivityContract.View>(),
	ProductDetailsActivityContract.Presenter<ProductDetailsActivityContract.View> {

	private var product: ProductModel? = null
	private var isOfferProduct: Boolean? = false
	private var isNewProduct: Boolean? = false
	private var isFeaturedProduct: Boolean? = false

	/**
	 * [ProductDetailsActivityContract.Presenter] implementation.
	 */
	@ExperimentalCoroutinesApi
	override fun getProduct(productId: Int) {
		view?.let { view ->
			view.showLoadingScreen()

			GlobalScope.launch(Dispatchers.IO) {
				val product = GlobalScope.async { getResult(getProductDetailUseCase.run(GetProductDetailUseCase.Params(productId))) }
				val isOfferProduct = GlobalScope.async { getResult(isOfferProductUseCase.run(IsOfferProductUseCase.Params(productId))) }
				val isNewProduct = GlobalScope.async { getResult(isNewProductUseCase.run(IsNewProductUseCase.Params(productId))) }
				val isFeaturedProduct = GlobalScope.async { getResult(isFeaturedProductUseCase.run(IsFeaturedProductUseCase.Params(productId))) }

				this@ProductDetailsActivityPresenter.let {
					it.product = product.await()
					it.isOfferProduct = isOfferProduct.await()
					it.isNewProduct = isNewProduct.await()
					it.isFeaturedProduct = isFeaturedProduct.await()
				}

				GlobalScope.launch(Dispatchers.Main) { onGetProductDetailSucces() }
			}
		}
	}

	private fun <T> getResult(either: Either<Any, T>): T? =
		when {
			either.isRight -> either.value()
			else -> null
		}

	private fun onGetProductDetailSucces() {
		view?.let { view: ProductDetailsActivityContract.View ->
			product?.let {
				view.setProductInfo(
					product = it,
					isNewProduct = isNewProduct ?: false,
					isOfferProduct = isOfferProduct ?: false,
					isFeaturedProduct = isFeaturedProduct ?: false
				)
				view.showInfoScreen()
			} ?: view.showErrorScreen()
		}
	}

}