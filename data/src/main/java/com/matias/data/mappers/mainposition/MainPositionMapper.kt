package com.matias.data.mappers.mainposition

import com.matias.data.entities.mainposition.MainPositionEntity
import com.matias.data.entities.novelty.NoveltyEntity
import com.matias.data.mappers.profile.ProfileMapper
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.mainposition.MainPositionModel
import com.matias.domain.models.novelty.NoveltyModel
import com.matias.domain.models.profile.ProfileModel

private const val INT_DEFAULT = -1
private const val STRING_DEFAULT = ""

class MainPositionMapper : Mapper<MainPositionEntity, MainPositionModel>() {

	override fun transformEntityToModel(e: MainPositionEntity): MainPositionModel {
		val profile: ProfileModel = ProfileMapper().transformEntityToModel(e.profile!!)
		val noveltyList: List<NoveltyModel> = tranformNoveltyListEntityToModel(e.noveltyList)

		return MainPositionModel(profile, noveltyList)
	}

	/**
	 * Novelty list entity -> Novelty list model.
	 */
	private fun tranformNoveltyListEntityToModel(noveltyListEntity: List<NoveltyEntity>?): List<NoveltyModel> {
		if (noveltyListEntity.isNullOrEmpty()) return listOf()

		// Do not add inactive novelties to model
		val activeNoveltyListEntity: ArrayList<NoveltyEntity> = arrayListOf()
		for (e in noveltyListEntity) {
			if (e.active == true) activeNoveltyListEntity.add(e)
		}

		return activeNoveltyListEntity.map { noveltyEntity: NoveltyEntity ->
			NoveltyModel(
				noveltyEntity.id ?: INT_DEFAULT,
				noveltyEntity.imageUrl ?: STRING_DEFAULT,
				noveltyEntity.title ?: STRING_DEFAULT,
				noveltyEntity.subtitle ?: STRING_DEFAULT,
				noveltyEntity.description ?: STRING_DEFAULT,
				noveltyEntity.termsAndConditions ?: STRING_DEFAULT,
				noveltyEntity.backgroundColor ?: STRING_DEFAULT
			)
		}
	}

}