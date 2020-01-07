package com.matias.features.login.ui.fragments.signin

import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.user.UserModel
import com.matias.domain.usecases.sigin.SignInUseCase
import javax.inject.Inject

class SignInFragmentPresenter @Inject constructor(
	private val signInUseCase: SignInUseCase
) : BasePresenter<SignInFragmentContract.View>(),
	SignInFragmentContract.Presenter<SignInFragmentContract.View> {

	override fun loginWithFacebook() {
		// TODO
	}

	override fun loginWithGoogle() {
		// TODO
	}

	override fun signIn(usernName: String?, password: String?, staySignedIn: Boolean) {
		view?.showLoading(true)
		if (usernName.isNullOrEmpty() || password.isNullOrEmpty()) {
			handleSignInError(FailureType.SignInEmptyCredentialsError())
			return
		}
		signInUseCase(
			{ it.either(::handleSignInError, ::handleSignInSuccess) },
			SignInUseCase.Params(usernName, password, staySignedIn)
		)
	}

	override fun handleSignInSuccess(userModel: UserModel) {
		view?.showLoading(false)
		view?.onSignInSuccess(userModel)
	}

	override fun handleSignInError(failureType: FailureType) {
		view?.apply {
			when (failureType) {
				is FailureType.SignInEmptyCredentialsError -> { onEmptyCredentialsError() }
				is FailureType.ServerError -> { onWrongCredentialsError(failureType.errorCode) }
			}
			showLoading(false)
		}
	}

}