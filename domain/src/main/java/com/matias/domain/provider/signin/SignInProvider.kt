package com.matias.domain.provider.signin

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import com.matias.domain.models.user.UserModel

interface SignInProvider {
    
    fun signIn(username: String, password: String, staySignedIn: Boolean): Either<FailureType, UserModel>
    
}