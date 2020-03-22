package com.matias.domain.provider.mainposition

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.mainposition.MainPositionModel

interface MainPositionProvider {

	fun getMainPosition(refresh: Boolean): Either<FailureType, MainPositionModel>

}