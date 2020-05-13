package com.matias.data.cache.mainposition

import com.matias.data.entities.mainposition.MainPositionEntity
import com.matias.data.mappers.mainposition.MainPositionMapper
import com.matias.domain.models.mainposition.MainPositionModel

class MainPositionCacheImpl : MainPositionCache {

	private var mainPositionModel: MainPositionModel? = null

	override fun storeMainPosition(mainPositionEntity: MainPositionEntity?) {
		mainPositionEntity?.let {
			mainPositionModel = MainPositionMapper().transformEntityToModel(it)
		} // TODO handle empty state.
	}

	override fun getMainPosition(): MainPositionModel =
		this.mainPositionModel ?: throw IllegalStateException("ERROR - Main Position empty")

}