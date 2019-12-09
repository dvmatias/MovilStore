package com.matias.domain.provider.splash

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.splash.GlobalConfigModel

interface SplashProvider {

    fun fetchGlobalConfig(arg: Any): Either<FailureType, GlobalConfigModel>

}