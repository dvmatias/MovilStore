package com.matias.data.cache.featureflag

import com.matias.data.entities.featureflag.FeatureFlagsEntity
import com.matias.domain.models.featureflag.FeatureFlagsModel
import com.matias.domain.models.featureflag.Features

interface FeatureFlaghCache {
    
    fun storeFeatureFlags(featureFlagsEntity: FeatureFlagsEntity?)
    
    fun getFeatureFlags(): FeatureFlagsModel?
    
}