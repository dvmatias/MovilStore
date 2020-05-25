package com.matias.data.service.product

import com.matias.data.entities.product.ProductEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class ProductService constructor(retrofit: Retrofit) : ProductApi {

	private val productApi: ProductApi by lazy { retrofit.create(ProductApi::class.java) }

	override fun getProduct(productId: Long): Call<ProductEntity> =
		productApi.getProduct(productId.toLong())
}