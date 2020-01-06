package com.matias.features.login.ui.fragments.signin

import android.content.Context
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
	BasePresenterFragment<SignInFragment, SignInFragmentPresenter, SignInFragmentSubcomponent>(),
	SignInFragmentContract.View {

	private var listener: LoginActivityContract.SignInFragmentInteractionListener? = null

	private var showPassword = false

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
		btnShowHidePassword.setOnClickListener{ handleBtnShowHidePasswordClick() }
	}

	private fun handleBtnShowHidePasswordClick() {
		if (!inputPassword.text.isNullOrEmpty()) {
			showPassword = !showPassword
			val imageResource = when (showPassword) {
				true -> { R.drawable.ic_show_password_24 }
				false -> { R.drawable.ic_hide_password_24 }
			}
			btnShowHidePassword.setImageResource(imageResource)
		} else {
			showPassword = false
			btnShowHidePassword.setImageResource(R.drawable.ic_hide_password_24)
		}
		showPassword(showPassword)
	}

	/**
	 * Called when a fragment is first attached to its context.
	 * [.onCreate] will be called after this.
	 */
	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is LoginActivityContract.SignInFragmentInteractionListener)
			listener = context
		else
			throw IllegalAccessException("Context $context should be implement LoginActivityContract.SignInFragmentInteractionListener")
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
		// TODO()
	}

	override fun onUserClickSignIn() {
		presenter.signIn(inputUserName.text.toString(), inputPassword.text.toString())
	}

	override fun onSignInSuccess(userModel: UserModel) {
		listener?.onSignInSuccess(userModel)
	}

	override fun onEmptyCredentialsError() {
		listener?.showEmptyCredentialsError(R.string.error_empty_credentials_sign_in)
	}

	override fun onWrongCredentialsError(errorCode: Int) {
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
		inputPassword.transformationMethod = if (show) null else PasswordTransformationMethod()
	}
}