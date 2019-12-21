package com.matias.features.login.ui.fragments.signin

import com.matias.core.base.mvp.BaseContract

interface SignInFragmentContract {

	interface View : BaseContract.View {

		fun onUserClickLoginWithFacebook()

		fun onUserClickLoginWithGoogle()

		fun onUserClickForgotPassword()

		fun onUserClickDontHaveAccount()

		fun onUserClickSignIn()

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {


	}

}