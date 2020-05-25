package com.matias.data.service.product

import com.matias.data.entities.product.ProductEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

	companion object {
		private const val CONTROLLER_PRODUCT = "products"

		private const val EP_GET_PRODUCT_BY_ID = "$CONTROLLER_PRODUCT/product"

		private const val QUERY_PRODUCT_ID = "product_id"
	}

	@GET(EP_GET_PRODUCT_BY_ID)
	fun getProduct(@Query(QUERY_PRODUCT_ID) productId: Long): Call<ProductEntity>

}