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

		fun showSignInError(errorMsgResource: Int)

		fun showSignUpErrorFields(errorMsgResource: Int)

		fun hideSignInError()

		fun goToMainScreen()

		fun showLoading(show: Boolean)

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
	interface FragmentInteractionListener {

		fun showEmptyCredentialsError(errorMsgResource: Int)

		fun showErrorBadCredentials(errorMsgResource: Int)

		fun hideCredentialsError()

		fun showMessagePasswordSent()

		fun onSignInSuccess(userModel: UserModel)

		fun showSignUpError(errorMsgResource: Int)

		fun onSignUpSuccess(userModel: UserModel)

		fun showLoading(show: Boolean)

		fun goToSignUpFragment()

	}

}