package com.matias.domain.usecases.mainposition

import com.matias.domain.models.novelty.NoveltyModel
import com.matias.domain.provider.mainposition.MainPositionProvider

class GetNoveltyListUseCase(
	private val mainPositionProvider: MainPositionProvider
) {

	fun get(): List<NoveltyModel> =
		mainPositionProvider.getMainPosition().noveltyList

}