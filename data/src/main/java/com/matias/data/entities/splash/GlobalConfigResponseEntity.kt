package com.matias.data.entities.splash

import com.google.gson.annotations.SerializedName

data class GlobalConfigResponseEntity(
        @SerializedName("splash_animation_type") val splashAnimationType: String?
)