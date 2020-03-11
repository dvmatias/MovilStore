package com.matias.features.login.ui.fragments.signin

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matias.components.button.flowbutton.StylingFlowButtonState
import com.matias.core.base.mvp.BasePresenterFragment
import com.matias.core.helpers.SimpleTextWatcher
import com.matias.domain.models.user.UserModel
import com.matias.features.R
import com.matias.features.login.di.fragments.signin.SignInFragmentModule
import com.matias.features.login.di.fragments.signin.SignInFragmentSubcomponent
import com.matias.features.login.ui.LoginUiComponent
import com.matias.features.login.ui.login.LoginActivityContract
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment :
	BasePresenterFragment<
			SignInFragment,
			SignInFragmentPresenter,
			SignInFragmentSubcomponent>(),
	SignInFragmentContract.View {

	private var listener: LoginActivityContract.FragmentInteractionListener? = null

	private var isPasswordVisible = false

	companion object {
		@JvmStatic
		fun newInstance(): SignInFragment =
			SignInFragment()

	}

	override fun bindComponent(): SignInFragmentSubcomponent =
		LoginUiComponent.component.plus(SignInFragmentModule())

	override fun bindLayout(): Int =
		R.layout.fragment_sign_in

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_sign_in, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		btnSignIn.buttonState = StylingFlowButtonState.STATE_ENABLED.state
		btnFacebook.setOnClickListener { onUserClickLoginWithFacebook() }
		btnGoogle.setOnClickListener { onUserClickLoginWithGoogle() }
		textForgotPassword.setOnClickListener { onUserClickForgotPassword() }
		textDontHaveAccount.setOnClickListener { onUserClickDontHaveAccount() }
		btnSignIn.setOnClickListener { onUserClickSignIn() }
		inputUserName.addTextChangedListener(object : SimpleTextWatcher() {
			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				super.onTextChanged(s, start, before, count)
				listener?.hideCredentialsError()
			}
		})
		inputPassword.addTextChangedListener(object : SimpleTextWatcher() {
			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				super.onTextChanged(s, start, before, count)
				listener?.hideCredentialsError()
			}
		})
		btnShowHidePassword.setOnClickListener { handleBtnShowHidePasswordClick() }
	}

	private fun handleBtnShowHidePasswordClick() {
		when (!inputPassword.text.isNullOrEmpty()) {
			true -> {
				showPassword(!isPasswordVisible)
			}
			false -> {
				showPassword(false)
			}
		}

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
	 * [SignInFragmentContract.View] implementation
	 */

	override fun onUserClickLoginWithFacebook() {
		presenter.loginWithFacebook()
	}

	override fun onUserClickLoginWithGoogle() {
		presenter.loginWithGoogle()
	}

	override fun onUserClickForgotPassword() {
		// TODO()
	}

	override fun onUserClickDontHaveAccount() {
		listener?.goToSignUpFragment()
	}

	override fun onUserClickSignIn() {
		btnSignIn.buttonState = StylingFlowButtonState.STATE_LOADING.state
		presenter.logIn(inputUserName.text.toString(), inputPassword.text.toString(), switchStayLoggedIn.isChecked)
	}

	override fun onSignInSuccess(userModel: UserModel) {
		btnSignIn.buttonState = StylingFlowButtonState.STATE_OK.state
		Handler().postDelayed({ listener?.onSignInSuccess(userModel) }, 350)
	}

	override fun onEmptyCredentialsError() {
		btnSignIn.buttonState = StylingFlowButtonState.STATE_ENABLED.state
		listener?.showEmptyCredentialsError(R.string.error_empty_credentials_sign_in)
	}

	override fun onWrongCredentialsError(errorCode: Int) {
		btnSignIn.buttonState = StylingFlowButtonState.STATE_ENABLED.state
		val errorMessageResource: Int = when (errorCode) {
			404 -> R.string.error_user_not_found_sign_in
			401 -> R.string.error_wrong_password_sign_in
			else -> -1
		}
		listener?.showErrorBadCredentials(errorMessageResource)
	}

	override fun showLoading(show: Boolean) {
		listener?.showLoading(show)
	}

	override fun showPassword(show: Boolean) {
		isPasswordVisible = show
		val imageResource = when (isPasswordVisible) {
			true -> R.drawable.ic_show_password_24
			false -> R.drawable.ic_hide_password_24
		}
		btnShowHidePassword.setImageResource(imageResource)
		inputPassword.transformationMethod = if (isPasswordVisible) null else PasswordTransformationMethod()
	}
}