package com.matias.domain.models.mainposition

import com.matias.domain.models.newproduct.NewProductModel
import com.matias.domain.models.novelty.NoveltyModel
import com.matias.domain.models.offer.OfferProductModel
import com.matias.domain.models.profile.ProfileModel

data class MainPositionModel (
	val profile: ProfileModel,
	val noveltyList: List<NoveltyModel>,
	val newProductList: ArrayList<NewProductModel>,
	val offerProductList: ArrayList<OfferProductModel>
)