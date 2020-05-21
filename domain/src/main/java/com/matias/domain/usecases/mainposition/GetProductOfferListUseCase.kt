package com.matias.domain.usecases.mainposition

import com.matias.domain.models.offer.ProductOfferModel
import com.matias.domain.provider.mainposition.MainPositionProvider

class GetProductOfferListUseCase(
	private val mainPositionProvider: MainPositionProvider
) {

	fun get(): ArrayList<ProductOfferModel> =
		mainPositionProvider.getMainPosition().productOfferList

}