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

	override fun signUpUser(email: String, psasword: String, userName: String, dateOfBirth: String, phone: String, gender: String) {
		if (isValidFields(email, psasword, userName, dateOfBirth, phone, gender)) {
			signUpUseCase(
				{ it.either(::handleSignUpFailure, ::handleSignUpSuccess) },
				SignUpUseCase.Params(email, psasword, userName, dateOfBirth, phone, gender)
			)
		} else {
			view?.apply {
				showLoading(false)
				handleSignUpFailure(FailureType.SignUpError.EmmptyFields)
			}
		}
	}

	override fun handleSignUpFailure(failureType: FailureType) {
		view?.apply {
			showLoading(false)
			when (failureType) {
				is FailureType.SignUpError.EmmptyFields -> onEmptyCredentialsError()
			}
		}
	}

	override fun handleSignUpSuccess(response: UserModel) {
		view?.apply {
			onSignUpSuccess(response)
			showLoading(false)
		}
	}

	override fun isValidFields(email: String, psasword: String, userName: String, dateOfBirth: String, phone: String, gender: String): Boolean =
			email.isNotEmpty() && psasword.isNotEmpty() && userName.isNotEmpty() && dateOfBirth.isNotEmpty() && phone.isNotEmpty() && gender.isNotEmpty()

}