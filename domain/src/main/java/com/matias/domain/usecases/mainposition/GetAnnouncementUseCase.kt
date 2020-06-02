package com.matias.domain.usecases.mainposition

import com.matias.domain.models.mainposition.AnnouncementModel
import com.matias.domain.models.novelty.NoveltyModel
import com.matias.domain.provider.mainposition.MainPositionProvider

class GetAnnouncementUseCase(
	private val mainPositionProvider: MainPositionProvider
) {

	fun get(): AnnouncementModel? =
		mainPositionProvider.getMainPosition().announcement

}