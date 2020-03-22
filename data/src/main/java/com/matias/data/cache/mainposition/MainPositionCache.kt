package com.matias.data.cache.mainposition

import com.matias.data.entities.mainposition.MainPositionEntity
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.mainposition.MainPositionModel

interface MainPositionCache {

	fun storeMainPosition(mainPositionEntity: MainPositionEntity?)

	fun getMainPosition(): Either<FailureType, MainPositionModel>

}