package com.matias.features.login.ui.fragments.signin

import com.matias.core.base.mvp.BaseContract
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.user.UserModel

interface SignInFragmentContract {

	interface View : BaseContract.View {

		fun onUserClickLoginWithFacebook()

		fun onUserClickLoginWithGoogle()

		fun onUserClickForgotPassword()

		fun onUserClickDontHaveAccount()

		fun onUserClickSignIn()

		fun onSignInSuccess(userModel: UserModel)

		fun onEmptyCredentialsError()

		fun onWrongCredentialsError(errorCode: Int)

		fun showLoading(show: Boolean)

		fun showPassword(show: Boolean)

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun loginWithFacebook()

		fun loginWithGoogle()

		fun logIn(usernName: String?, password: String?, stayLoggedIn: Boolean)

		fun handleLogInSuccess(userModel: UserModel)

		fun handleLogInError(failureType: FailureType)

	}

}