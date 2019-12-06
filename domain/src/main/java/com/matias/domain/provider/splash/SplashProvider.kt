package com.matias.domain.provider.splash

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either

interface SplashProvider {

    fun doSomething(arg: Any): Either<FailureType, Unit>

}