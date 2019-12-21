package com.matias.features.login.ui.login

import com.matias.core.base.mvp.BaseContract
import com.matias.features.login.ui.fragments.signin.SignInFragment

/**
 * MVP Contract.
 */
interface LoginActivityContract {

	/**
	 * @see LoginActivity client
	 */
	interface View : BaseContract.View {

		fun showSignInError(errorMsg: String)

	}

	/**
	 * @see LoginActivityPresenter client
	 */
	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun loginWithFacebook()

		fun loginWithGoogle()

		fun signIn(usernName: String?, password: String?)

		fun handleSignInSuccess()
		
		fun handleSignInError()
		
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

		fun onUserClickSignIn()

		fun goToMainScreen()

		fun showErrorBadCredentials()

		fun showMessagePasswordSent()

	}

}