package com.matias.data.cache.featureflag

import com.matias.data.entities.featureflag.FeatureFlagsEntity
import com.matias.data.mappers.featureflag.FeatureFlagMapper
import com.matias.domain.models.featureflag.FeatureFlagsModel

class FeatureFlaghCacheImpl : FeatureFlaghCache {

    private var featureFlagsModel: FeatureFlagsModel? = null

    override fun storeFeatureFlags(featureFlagsEntity: FeatureFlagsEntity?) {
        featureFlagsEntity?.let {
            featureFlagsModel = FeatureFlagMapper().transformEntityToModel(it)
        } ?: throw IllegalStateException("Feature flags not set by service")
    }

    override fun getFeatureFlags(): FeatureFlagsModel? = featureFlagsModel

}