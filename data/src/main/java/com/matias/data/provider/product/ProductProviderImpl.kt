package com.matias.data.provider.product

import com.matias.data.cache.products.ProductCache
import com.matias.data.mappers.product.ProductMapper
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.NetworkProvider
import com.matias.data.service.product.ProductApi
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.product.ProductModel
import com.matias.domain.provider.product.ProductProvider

class ProductProviderImpl(
	private val service: ProductApi,
	private val cache: ProductCache,
	networkHandler: NetworkHandler
) : ProductProvider, NetworkProvider(networkHandler) {

	override fun getProduct(productId: Int): Either<FailureType, ProductModel> {
		val cahchedProduct: ProductModel? = cache.getProduct(productId)
		return if (cahchedProduct != null) {
			Either.Right(cahchedProduct)
		} else {
			request(service.getProduct(productId.toLong())) {
				val product = ProductMapper().transformEntityToModel(it)
				cache.insertProduct(product)
				product
			}
		}
	}

	override fun isOfferProduct(productId: Int): Either<FailureType, Boolean> =
		request(service.isOfferProduct(productId.toLong())) {
			it
		}

	override fun isNewProduct(productId: Int): Either<FailureType, Boolean> =
		request(service.isNewProduct(productId.toLong())) {
			it
		}

	override fun isFeaturedProduct(productId: Int): Either<FailureType, Boolean> =
		request(service.isFeaturedProduct(productId.toLong())) {
			it
		}
}