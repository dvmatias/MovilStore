package com.matias.features.di

import com.matias.data.cache.mainposition.MainPositionCache
import com.matias.data.cache.mainposition.MainPositionCacheImpl
import com.matias.data.cache.products.ProductCache
import com.matias.data.cache.products.ProductCacheImpl
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.product.ProductProviderImpl
import com.matias.data.service.product.ProductApi
import com.matias.data.service.product.ProductService
import com.matias.domain.provider.product.ProductProvider
import com.matias.domain.usecases.product.GetProductDetailUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class ProductDetailsModule {

	@Provides
	@ProductDetailsScope
	fun provideProductApi(retrofit: Retrofit): ProductApi =
		ProductService(retrofit)

	@Provides
	@ProductDetailsScope
	fun provideProductCache(): ProductCache =
		ProductCacheImpl()

	@Provides
	@ProductDetailsScope
	fun provideProductProvider(
		productApi: ProductApi,
		productCache: ProductCache,
		networkHandler: NetworkHandler
	): ProductProvider =
		ProductProviderImpl(productApi, productCache, networkHandler)

	@Provides
	fun provideGetProductDetailUseCase(productProvider: ProductProvider): GetProductDetailUseCase =
		GetProductDetailUseCase(productProvider)

}