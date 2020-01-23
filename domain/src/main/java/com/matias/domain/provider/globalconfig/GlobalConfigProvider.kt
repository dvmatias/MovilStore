package com.matias.domain.provider.globalconfig

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.globalconfig.GlobalConfigModel

interface GlobalConfigProvider {
    
    fun fetchGlobalConfig(arg: Any): Either<FailureType, GlobalConfigModel>
    
}