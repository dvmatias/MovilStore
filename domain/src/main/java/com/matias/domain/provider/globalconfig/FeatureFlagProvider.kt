package com.matias.domain.provider.globalconfig

import com.matias.domain.models.featureflag.Features

interface FeatureFlagProvider {

	fun getFeatureFlagStatus(feature: Features): Boolean

}