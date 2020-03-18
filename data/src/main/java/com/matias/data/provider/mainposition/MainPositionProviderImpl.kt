package com.matias.data.provider.mainposition

import com.matias.data.mappers.mainposition.MainPositionMapper
import com.matias.data.platform.NetworkHandler
import com.matias.data.provider.NetworkProvider
import com.matias.data.service.main.MainPositionApi
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.mainposition.MainPositionModel
import com.matias.domain.provider.mainposition.MainPositionProvider

class MainPositionProviderImpl(
	private val mainPositionApi: MainPositionApi,
	networkHandler: NetworkHandler
) : MainPositionProvider, NetworkProvider(networkHandler) {

	override fun getMainPosition(userId: Int): Either<FailureType, MainPositionModel> =
		request(mainPositionApi.getMainPosition()) {
			MainPositionMapper().transformEntityToModel(it)
		}

}