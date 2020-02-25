package com.matias.domain.usecases.globalconfig

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.models.featureflag.Features
import com.matias.domain.provider.globalconfig.FeatureFlagProvider

class GetFeatureEnableUseCase(
	private val featureFlagProvider: FeatureFlagProvider
)  {

	fun isFeatureAvailable(params: Params): Boolean =
		featureFlagProvider.getFeatureFlagStatus(params.feature)

	data class Params(val feature: Features)

}