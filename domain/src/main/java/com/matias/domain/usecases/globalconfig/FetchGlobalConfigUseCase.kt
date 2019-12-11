package com.matias.domain.usecases.globalconfig

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.base.usecase.UseCase
import com.matias.domain.models.globalconfig.GlobalConfigModel
import com.matias.domain.provider.globalconfig.GlobalConfigProvider

class FetchGlobalConfigUseCase(
        private val globalConfigProvider: GlobalConfigProvider
) : UseCase<GlobalConfigModel, FetchGlobalConfigUseCase.Params>() {

    override suspend fun run(params: Params): Either<FailureType, GlobalConfigModel> =
            globalConfigProvider.fetchGlobalConfig(params.arg)

    data class Params(val arg: Any)

}