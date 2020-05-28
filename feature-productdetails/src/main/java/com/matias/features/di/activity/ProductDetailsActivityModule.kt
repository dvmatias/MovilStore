package com.matias.features.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.matias.domain.provider.product.ProductProvider
import com.matias.domain.usecases.product.GetProductDetailUseCase
import com.matias.domain.usecases.product.IsFeaturedProductUseCase
import com.matias.domain.usecases.product.IsNewProductUseCase
import com.matias.domain.usecases.product.IsOfferProductUseCase
import com.matias.features.ui.productdetails.adapters.RecyclerMultimediaAdapter
import dagger.Module
import dagger.Provides

@Module
class ProductDetailsActivityModule(private val activity: AppCompatActivity) {

	@Provides
	fun provideActivityContext(): AppCompatActivity = this.activity

	@Provides
	fun provideGetProductDetailUseCase(productProvider: ProductProvider): GetProductDetailUseCase =
		GetProductDetailUseCase(productProvider)

	@Provides
	fun provideIsOfferProductUseCase(productProvider: ProductProvider): IsOfferProductUseCase =
		IsOfferProductUseCase(productProvider)

	@Provides
	fun provideIsNewProductUseCase(productProvider: ProductProvider): IsNewProductUseCase =
		IsNewProductUseCase(productProvider)

	@Provides
	fun provideIsFeaturedProductUseCase(productProvider: ProductProvider): IsFeaturedProductUseCase =
		IsFeaturedProductUseCase(productProvider)

	@Provides
	fun provideRecyclerMultimediaAdapter(activity: AppCompatActivity): RecyclerMultimediaAdapter =
		RecyclerMultimediaAdapter(activity)

}