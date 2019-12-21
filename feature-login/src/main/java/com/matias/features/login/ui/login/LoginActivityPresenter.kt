package com.matias.features.login.ui.login

import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.user.UserModel
import com.matias.domain.usecases.sigin.SignInUseCase
import javax.inject.Inject

class LoginActivityPresenter @Inject constructor(
	private val signInUseCase: SignInUseCase
) : BasePresenter<LoginActivityContract.View>(),
	LoginActivityContract.Presenter<LoginActivityContract.View> {

	override fun loginWithFacebook() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun loginWithGoogle() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun signIn(usernName: String?, password: String?) {
		if (usernName.isNullOrEmpty() || password.isNullOrEmpty()) {
			handleSignInError(FailureType.SignInEmptyCredentialsError())
			return
		}
		signInUseCase(
			{ it.either(::handleSignInError, ::handleSignInSuccess) },
			SignInUseCase.Params(usernName, password)
		)
	}

	override fun handleSignInSuccess(userModel: UserModel) {
		// TODO() mannage 'stay signed in' option.
		// TODO() Hide loading
		// TODO() Go to Main Screen
		view?.goToMainScreen()
	}

	override fun handleSignInError(failureType: FailureType) {
		view?.apply {
			when (failureType) {
				is FailureType.SignInEmptyCredentialsError -> {
					showEmptyCredentialsError(true)
				}
			}
		}
	}

	override fun staySignedIn() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}