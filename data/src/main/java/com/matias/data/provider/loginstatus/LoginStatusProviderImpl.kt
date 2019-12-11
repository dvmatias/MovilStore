package com.matias.data.provider.loginstatus

import com.matias.data.cache.loginstatus.SharedPreferencesCache
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.provider.loginstatus.LoginStatusProvider

class LoginStatusProviderImpl(
        private val sharedPreferencesCache: SharedPreferencesCache
) : LoginStatusProvider {

    override fun getLoginStatus(): Either<FailureType, Boolean> =
            if (true) Either.Right(true) else Either.Left(FailureType.LocalError())
    // TODO implement right logic to get login status

}