package com.matias.domain.usecases.mainposition

import com.matias.domain.models.offer.OfferProductModel
import com.matias.domain.provider.mainposition.MainPositionProvider

class GetOfferProductListUseCase(
	private val mainPositionProvider: MainPositionProvider
) {

	fun get(): ArrayList<OfferProductModel> =
		mainPositionProvider.getMainPosition().offerProductList

}