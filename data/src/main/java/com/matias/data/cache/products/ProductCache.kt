package com.matias.data.cache.products

import com.matias.domain.models.product.ProductModel

interface ProductCache {

	fun insertProduct(product: ProductModel)

	fun getProduct(productId: Int): ProductModel?

}