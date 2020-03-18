package com.matias.data.mappers.mainposition

import com.matias.data.entities.mainposition.MainPositionEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.mainposition.MainPositionModel

class MainPositionMapper : Mapper<MainPositionEntity, MainPositionModel>() {

	override fun transformEntityToModel(e: MainPositionEntity): MainPositionModel {
		// TODO
		return MainPositionModel("a")
	}
}