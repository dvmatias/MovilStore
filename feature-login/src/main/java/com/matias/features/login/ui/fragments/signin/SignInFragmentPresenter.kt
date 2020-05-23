package com.matias.features.login.ui.fragments.signin

import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.user.UserModel
import com.matias.domain.usecases.login.LogInUseCase
import javax.inject.Inject

class SignInFragmentPresenter @Inject constructor(
        private val logInUseCase: LogInUseCase
) : BasePresenter<SignInFragmentContract.View>(),
        SignInFragmentContract.Presenter<SignInFragmentContract.View> {
    
    override fun loginWithFacebook() {
        // TODO
    }
    
    override fun loginWithGoogle() {
        // TODO
    }

    override fun logIn(usernName: String?, password: String?, stayLoggedIn: Boolean) {
        when (usernName.isNullOrEmpty() || password.isNullOrEmpty()) {
            true -> handleLogInError(FailureType.LogInErrorType.EmptyCredentialsErrorType)
            false -> {
                view?.apply { showLoading(true); showPassword(false) }
                logInUseCase(
                        { it.either(::handleLogInError, ::handleLogInSuccess) },
                        LogInUseCase.Params(usernName, password, stayLoggedIn)
                )
            }
        }
    }

    override fun handleLogInSuccess(userModel: UserModel) {
        view?.apply {
            showLoading(false)
            onSignInSuccess(userModel)
        }
    }

    override fun handleLogInError(failureType: FailureType) {
        view?.apply {
            when (failureType) {
                is FailureType.LogInErrorType.EmptyCredentialsErrorType -> onEmptyCredentialsError()
                else -> onWrongCredentialsError(404) // TODO mannage properly
            }
            showLoading(false)
        }
    }
}