package com.matias.features.login.ui.fragments.signup

import com.matias.core.base.mvp.BasePresenter
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.user.UserModel
import com.matias.domain.usecases.login.SignUpUseCase
import javax.inject.Inject

class SignUpFragmentPresenter @Inject constructor(
	private val signUpUseCase: SignUpUseCase
) : BasePresenter<SignUpFragmentContract.View>(),
	SignUpFragmentContract.Presenter<SignUpFragmentContract.View> {

	override fun signUpUser(email: String, psasword: String, userName: String, birthDate: String, phone: String, gender: String) {
		when (isValidFields(email, psasword, userName, birthDate, phone, gender)) {
			true -> {
				signUpUseCase(
					{ it.either(::handleSignUpFailure, ::handleSignUpSuccess) },
					SignUpUseCase.Params(email, psasword, userName, birthDate, phone, gender)
				)
			}
			false -> {
				view?.apply {
					showLoading(false)
					handleSignUpFailure(FailureType.SignUpError.EmptyFieldsLocalException())
				}
			}
		}
	}

	override fun handleSignUpFailure(failureType: FailureType) {
		view?.apply {
			showLoading(false)
			when (failureType) {
				is FailureType.SignUpError.EmptyFieldsLocalException -> onEmptyCredentialsError()
			}
		}
	}

	override fun handleSignUpSuccess(response: UserModel) {
		view?.apply {
			onSignUpSuccess(response)
			showLoading(false)
		}
	}

	override fun isValidFields(email: String, psasword: String, userName: String, birthDate: String, phone: String, gender: String): Boolean =
			email.isNotEmpty() && psasword.isNotEmpty() && userName.isNotEmpty() && birthDate.isNotEmpty() && phone.isNotEmpty() && gender.isNotEmpty()

}