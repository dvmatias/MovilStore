package com.matias.features.login.ui.login

import com.matias.core.base.mvp.BaseContract
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.user.UserModel
import com.matias.features.login.ui.fragments.signin.SignInFragment

/**
 * MVP Contract.
 */
interface LoginActivityContract {

	/**
	 * @see LoginActivity client
	 */
	interface View : BaseContract.View {

		fun showEmptyCredentialsError(show: Boolean)

		fun showSignInError(errorMsg: String)

		fun goToMainScreen()

	}

	/**
	 * @see LoginActivityPresenter client
	 */
	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun loginWithFacebook()

		fun loginWithGoogle()

		fun signIn(usernName: String?, password: String?)

		fun handleSignInSuccess(userModel: UserModel)

		fun handleSignInError(failureType: FailureType)

		fun staySignedIn()

	}

	/**
	 * Interface to communicate user events from [SignInFragment] to [LoginActivity].
	 *
	 * @see LoginActivity client
	 */
	interface SignInFragmentInteractionListener {

		fun onUserClickLoginWithFacebook()

		fun onUserClickLoginWithGoogle()

		fun onUserClickForgotPassword()

		fun onUserClickDontHaveAccount()

		fun onUserClickSignIn(usernName: String?, password: String?)

		fun showErrorBadCredentials()

		fun showMessagePasswordSent()

		fun hideEmptyCredentialsError()

	}

}