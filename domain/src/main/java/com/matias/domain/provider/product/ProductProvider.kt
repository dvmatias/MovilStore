package com.matias.domain.provider.product

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.product.ProductModel

interface ProductProvider {


	fun getProduct(productId: Int): Either<FailureType, ProductModel>

//	fun fetchProduct(productId: Long): Either<FailureType, ProductModel>
//
//	fun getProduct(productId: Long)

}