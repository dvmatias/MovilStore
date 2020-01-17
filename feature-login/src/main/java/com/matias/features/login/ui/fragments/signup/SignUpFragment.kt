package com.matias.features.login.ui.fragments.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matias.core.base.mvp.BasePresenterFragment
import com.matias.domain.models.user.UserModel
import com.matias.features.R
import com.matias.features.login.di.fragments.signup.SignUpFragmentModule
import com.matias.features.login.di.fragments.signup.SignUpFragmentSubcomponent
import com.matias.features.login.ui.LoginUiComponent
import com.matias.features.login.ui.login.LoginActivityContract
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BasePresenterFragment<
		SignUpFragment,
		SignUpFragmentPresenter,
		SignUpFragmentSubcomponent>(), SignUpFragmentContract.View {

	private var listener: LoginActivityContract.FragmentInteractionListener? = null

	companion object {
		@JvmStatic
		fun newInstance(): SignUpFragment =
			SignUpFragment()
	}

	override fun bindComponent(): SignUpFragmentSubcomponent =
		LoginUiComponent.component.plus(SignUpFragmentModule())

	override fun bindLayout(): Int =
		R.layout.fragment_sign_up

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
		inflater.inflate(R.layout.fragment_sign_up, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		btnSignUp.setOnClickListener { onButtonPressed() }
	}

	private fun onButtonPressed() {
		presenter.signUpUser(inputEmail.text.toString(),
			inputPassword.text.toString(),
			inputUsername.text.toString(),
			inputDateOfBirth.text.toString(),
			inputPhone.text.toString(),
			inputGender.text.toString()
		)
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is LoginActivityContract.FragmentInteractionListener)
			listener = context
		else
			throw IllegalAccessException("Context $context should be implement LoginActivityContract.FragmentInteractionListener")
	}

	override fun onDetach() {
		super.onDetach()
		listener = null
	}

	/**
	 * [SignUpFragmentContract.View] implementation
	 */

	override fun showLoading(show: Boolean) {
		listener?.showLoading(show)
	}

	override fun onSignUpSuccess(userModel: UserModel) {
		listener?.onSignUpSuccess(userModel)
	}

	override fun onEmptyCredentialsError() {
		listener?.showSignUpError(R.string.error_empty_credentials_sign_up)
	}

}