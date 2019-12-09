package com.matias.data.entities.splash

import com.google.gson.annotations.SerializedName
import com.matias.data.entities.featureflag.FeatureFlagsEntity

data class GlobalConfigResponseEntity(
        @SerializedName("splash_animation_type") val splashAnimationType: String?,
        @SerializedName("feature_flags") val featureFlagsEntity: FeatureFlagsEntity?
)