package com.matias.features.login.ui.fragments.signup

import com.matias.core.base.mvp.BaseContract
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.user.UserModel

interface SignUpFragmentContract {

	interface View : BaseContract.View {

		fun onUserClickSignUp()

		fun onSignUpSuccess(userModel: UserModel)

		fun onSignUpFailure(errorCode: Int)

		fun onEmptyCredentialsError()

		fun showBirthDatePicker()

		fun showLoading(show: Boolean)

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun signUpUser(email: String, psasword: String, userName: String, birthDate: String, phone: String, gender: String)

		fun handleSignUpFailure(failureType: FailureType)

		fun handleSignUpSuccess(response: UserModel)

		fun isValidFields(email: String, psasword: String, userName: String, birthDate: String, phone: String, gender: String): Boolean

	}

}