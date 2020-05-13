package com.matias.domain.provider.login

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.user.UserModel

interface LogInProvider {
    
    fun logIn(username: String, password: String, stayLoggedIn: Boolean): Either<FailureType, UserModel>
    
}