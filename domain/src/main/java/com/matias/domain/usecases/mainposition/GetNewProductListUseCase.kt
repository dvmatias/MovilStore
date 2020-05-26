package com.matias.domain.usecases.mainposition

import com.matias.domain.models.newproduct.NewProductModel
import com.matias.domain.models.offer.OfferProductModel
import com.matias.domain.provider.mainposition.MainPositionProvider

class GetNewProductListUseCase(
	private val mainPositionProvider: MainPositionProvider
) {

	fun get(): ArrayList<NewProductModel> =
		mainPositionProvider.getMainPosition().newProductList

}