package com.matias.domain.usecases.product

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.provider.product.ProductProvider

class IsNewProductUseCase(
	private val productProvider: ProductProvider
) : UseCase<Boolean, IsNewProductUseCase.Params>() {

	override suspend fun run(params: Params): Either<FailureType, Boolean> =
		productProvider.isNewProduct(params.productId)

	data class Params(val productId: Int)

}