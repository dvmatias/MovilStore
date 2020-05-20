package com.matias.data.mappers.mainposition

import com.matias.data.entities.mainposition.MainPositionEntity
import com.matias.data.entities.novelty.NoveltyEntity
import com.matias.data.entities.product.ProductEntity
import com.matias.data.mappers.profile.ProfileMapper
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.mainposition.MainPositionModel
import com.matias.domain.models.novelty.NoveltyModel
import com.matias.domain.models.offer.ProductOfferModel
import com.matias.domain.models.profile.ProfileModel

private const val INT_DEFAULT = -1
private const val STRING_DEFAULT = ""
private const val FLOAT_DEFAULT = 0F

class MainPositionMapper : Mapper<MainPositionEntity, MainPositionModel>() {

	override fun transformEntityToModel(e: MainPositionEntity): MainPositionModel {
		val profile: ProfileModel = ProfileMapper().transformEntityToModel(e.profile!!)
		val noveltyList: List<NoveltyModel> = tranformNoveltyListEntityToModel(e.noveltyList)
		val productOfferList: ArrayList<ProductOfferModel> = transformOfferListEntityToModel(e.productOfferList)

		return MainPositionModel(profile, noveltyList, productOfferList)
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

	/**
	 *
	 */
	private fun transformOfferListEntityToModel(productOfferListEntity: List<ProductEntity>?): ArrayList<ProductOfferModel> {
		if (productOfferListEntity.isNullOrEmpty()) return arrayListOf()

		val productOfferList = arrayListOf<ProductOfferModel>()
		for (productEntity in productOfferListEntity) {
			productEntity.let {
				productOfferList.add(
					ProductOfferModel(
						it.id ?: INT_DEFAULT,
						it.price ?: FLOAT_DEFAULT,
						it.originalPrice ?: FLOAT_DEFAULT,
						it.title ?: STRING_DEFAULT,
						it.subtitle ?: STRING_DEFAULT,
						it.thumbnailUrl ?: STRING_DEFAULT
					)
				)
			}
		}

		return productOfferList
	}

}