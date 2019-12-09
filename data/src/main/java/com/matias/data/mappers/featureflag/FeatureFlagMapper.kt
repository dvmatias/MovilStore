package com.matias.data.mappers.featureflag

import com.matias.data.entities.featureflag.FeatureFlagsEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.featureflag.FeatureFlagsModel

class FeatureFlagMapper : Mapper<FeatureFlagsEntity, FeatureFlagsModel>() {

    override fun transformEntityToModel(e: FeatureFlagsEntity): FeatureFlagsModel {
        val mainScrenHomeTabEnable = e.mainScrenHomeTabEnable ?: false
        val mainScrenEcommerceTabEnable = e.mainScrenEcommerceTabEnable ?: false
        val mainScrenContactUsTabEnable = e.mainScrenContactUsTabEnable ?: false

        return FeatureFlagsModel(
                mainScrenHomeTabEnable,
                mainScrenEcommerceTabEnable,
                mainScrenContactUsTabEnable
        )
    }
}