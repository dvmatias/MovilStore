package com.matias.features.login.ui.fragments.signup

import com.matias.core.base.mvp.BaseContract
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.user.UserModel

interface SignUpFragmentContract {

	interface View : BaseContract.View {

		fun onSignUpSuccess(userModel: UserModel)

		fun onEmptyCredentialsError()

		fun showLoading(show: Boolean)

	}

	interface Presenter<V : View> : BaseContract.Presenter<V> {

		fun signUpUser(email: String, psasword: String, userName: String, dateOfBirth: String, phone: String, gender: String)

		fun handleSignUpFailure(failureType: FailureType)

		fun handleSignUpSuccess(response: UserModel)

		fun isValidFields(email: String, psasword: String, userName: String, dateOfBirth: String, phone: String, gender: String): Boolean

	}

}