package com.matias.data.service.product

import com.matias.data.entities.product.ProductEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

	companion object {
		private const val CONTROLLER_PRODUCT = "products"

		private const val EP_GET_PRODUCT_BY_ID = "$CONTROLLER_PRODUCT/product"
		private const val EP_IS_OFFER_PRODUCT = "$CONTROLLER_PRODUCT/is-offer-product"
		private const val EP_IS_NEW_PRODUCT = "$CONTROLLER_PRODUCT/is-new-product"
		private const val EP_IS_FEATURED_PRODUCT = "$CONTROLLER_PRODUCT/is-featured-product"

		private const val QUERY_PRODUCT_ID = "product_id"
	}

	@GET(EP_GET_PRODUCT_BY_ID)
	fun getProduct(@Query(QUERY_PRODUCT_ID) productId: Long): Call<ProductEntity>

	@GET(EP_IS_OFFER_PRODUCT)
	fun isOfferProduct(@Query(QUERY_PRODUCT_ID) productId: Long): Call<Boolean>

	@GET(EP_IS_NEW_PRODUCT)
	fun isNewProduct(@Query(QUERY_PRODUCT_ID) productId: Long): Call<Boolean>

	@GET(EP_IS_FEATURED_PRODUCT)
	fun isFeaturedProduct(@Query(QUERY_PRODUCT_ID) productId: Long): Call<Boolean>

}