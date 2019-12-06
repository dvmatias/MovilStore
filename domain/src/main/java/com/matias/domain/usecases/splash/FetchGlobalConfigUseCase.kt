package com.matias.domain.usecases.splash

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.provider.splash.SplashProvider

class FetchGlobalConfigUseCase(
        private val splashProvider: SplashProvider
) : UseCase<Unit, FetchGlobalConfigUseCase.Params>() {

    override suspend fun run(params: Params): Either<FailureType, Unit> =
            splashProvider.doSomething(params.arg)

    data class Params(val arg: Any)

}