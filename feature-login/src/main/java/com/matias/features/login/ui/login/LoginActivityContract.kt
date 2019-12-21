package com.matias.features.login.ui.login

import com.matias.core.base.mvp.BaseContract
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

		fun showSignInError(errorMsg: String)

		fun goToMainScreen()

	}

	/**
	 * @see LoginActivityPresenter client
	 */
	interface Presenter<V : View> : BaseContract.Presenter<V>

	/**
	 * Interface to communicate user events from [SignInFragment] to [LoginActivity].
	 *
	 * @see LoginActivity client
	 */
	interface SignInFragmentInteractionListener {

		fun showErrorBadCredentials(errorMessageResource: Int)

		fun showMessagePasswordSent()

		fun onSignInSuccess(userModel: UserModel)

		fun showEmptyCredentialsError()

		fun hideError()

	}

}