package com.matias.domain.provider.loginstatus

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either

interface LoginStatusProvider {
    
    fun getLoginStatus(): Either<FailureType, Boolean>
    
}