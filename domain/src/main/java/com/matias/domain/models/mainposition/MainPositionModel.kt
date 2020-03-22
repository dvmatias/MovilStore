package com.matias.domain.models.mainposition

import com.matias.domain.models.novelty.NoveltyModel
import com.matias.domain.models.profile.ProfileModel

data class MainPositionModel (
	val profile: ProfileModel,
	val noveltyList: List<NoveltyModel>
)