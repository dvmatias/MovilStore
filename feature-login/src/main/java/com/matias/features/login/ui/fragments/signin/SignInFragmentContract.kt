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

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun loginWithFacebook()

		fun loginWithGoogle()

		fun signIn(usernName: String?, password: String?)

		fun handleSignInSuccess(userModel: UserModel)

		fun handleSignInError(failureType: FailureType)

	}

}