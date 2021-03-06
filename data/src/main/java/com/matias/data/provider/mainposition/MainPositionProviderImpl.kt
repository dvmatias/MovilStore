package com.matias.data.provider.mainposition

import com.matias.data.cache.mainposition.MainPositionCache
import com.matias.data.entities.mainposition.MainPositionEntity
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
	private val mainPositionCache: MainPositionCache,
	networkHandler: NetworkHandler
) : MainPositionProvider, NetworkProvider(networkHandler) {

	/**
	 * From server.
	 */
	override fun fetchMainPosition(): Either<FailureType, MainPositionModel> =
		request(mainPositionApi.getMainPosition()) { e: MainPositionEntity ->
			mainPositionCache.storeMainPosition(e)
			MainPositionMapper().transformEntityToModel(e)
		}

	/**
	 * From cache.
	 */
	override fun getMainPosition(): MainPositionModel =
		mainPositionCache.getMainPosition()
}