package com.matias.domain.provider.splash

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.splash.GlobalConfigResponseModel

interface SplashProvider {

    fun fetchGlobalConfig(arg: Any): Either<FailureType, GlobalConfigResponseModel>

}