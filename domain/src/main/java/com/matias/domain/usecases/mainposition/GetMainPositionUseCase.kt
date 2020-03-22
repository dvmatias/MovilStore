package com.matias.domain.usecases.mainposition

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.models.mainposition.MainPositionModel
import com.matias.domain.provider.mainposition.MainPositionProvider

class GetMainPositionUseCase(
	private val mainPositionProvider: MainPositionProvider
) : UseCase<MainPositionModel, GetMainPositionUseCase.Params>() {

	override suspend fun run(params: Params): Either<FailureType, MainPositionModel> =
		mainPositionProvider.getMainPosition(params.refresh)

	class Params(val refresh: Boolean)
}