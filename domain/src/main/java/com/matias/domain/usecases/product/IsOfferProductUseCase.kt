package com.matias.domain.usecases.product

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.provider.product.ProductProvider

class IsOfferProductUseCase(
	private val productProvider: ProductProvider
) : UseCase<Boolean, IsOfferProductUseCase.Params>() {

	override suspend fun run(params: Params): Either<FailureType, Boolean> =
		productProvider.isOfferProduct(params.productId)

	data class Params(val productId: Int)

}