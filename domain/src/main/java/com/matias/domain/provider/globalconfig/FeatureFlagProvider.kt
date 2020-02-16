package com.matias.domain.provider.globalconfig

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.featureflag.Features

interface FeatureFlagProvider {

	fun getFeatureFlagStatus(feature: Features): Either<FailureType, Boolean>

}