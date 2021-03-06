package com.matias.domain.usecases.product

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.models.product.ProductModel
import com.matias.domain.provider.product.ProductProvider

class GetProductDetailUseCase(
	private val productProvider: ProductProvider
) : UseCase<ProductModel, GetProductDetailUseCase.Params>() {

	override suspend fun run(params: Params): Either<FailureType, ProductModel> =
		productProvider.getProduct(params.productId)

	data class Params(val productId: Int)
}
