package com.matias.domain.provider.mainposition

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.mainposition.MainPositionModel

interface MainPositionProvider {

	fun fetchMainPosition(): Either<FailureType, MainPositionModel>

	fun getMainPosition(): MainPositionModel

}