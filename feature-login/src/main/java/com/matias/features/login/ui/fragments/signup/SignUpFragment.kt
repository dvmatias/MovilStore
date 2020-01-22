package com.matias.features.login.ui.fragments.signup

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.matias.components.button.flowbutton.StylingFlowButtonStatus
import com.matias.components.datepicker.StylingDatePicker
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

	private lateinit var genders: Array<String>

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

		btnSignUp.buttonStatus = StylingFlowButtonStatus.STATUS_ENABLED.status
		inputBirthDate.setOnClickListener { showBirthDatePicker() }
		inputGender.setOnClickListener { spinnerGender.performClick() }
		setupSpinnerGender()
		btnSignUp.setOnClickListener { onUserClickSignUp() }
	}

	private fun setupSpinnerGender() {
		genders = activity!!.resources.getStringArray(R.array.genders)
		// Create an ArrayAdapter using a simple spinner layout and languages array
		val aa = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, genders)
		// Set layout to use when the list of choices appear
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		// Set Adapter to Spinner
		spinnerGender!!.adapter = aa
		spinnerGender!!.onItemSelectedListener = spinnerListener
		inputGender.setText("")

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

	private val spinnerListener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
		override fun onNothingSelected(p0: AdapterView<*>?) {}

		override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
			view?.apply { (view as TextView).setTextColor(Color.TRANSPARENT) }
			if (position > 0) inputGender.setText(genders[position])
		}
	}

	/**
	 * [SignUpFragmentContract.View] implementation
	 */

	override fun onUserClickSignUp() {
		btnSignUp.buttonStatus = StylingFlowButtonStatus.STATUS_LOADING.status
		presenter.signUpUser(
			inputEmail.text.toString(),
			inputPassword.text.toString(),
			inputUsername.text.toString(),
			inputBirthDate.text.toString(),
			inputPhone.text.toString(),
			inputGender.text.toString()
		)
	}

	override fun showLoading(show: Boolean) {
		listener?.showLoading(show)
	}

	override fun showBirthDatePicker() {
		val newFragment = StylingDatePicker.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
			val selectedDate = "${String.format("%02d", day)}/${String.format("%02d", (month + 1))}/$year"
			inputBirthDate.setText(selectedDate)
		})

		newFragment.show(activity!!.supportFragmentManager, "datePicker")
	}

	override fun onSignUpSuccess(userModel: UserModel) {
		btnSignUp.buttonStatus = StylingFlowButtonStatus.STATUS_OK.status
		Handler().postDelayed({ listener?.onSignUpSuccess(userModel) }, 350)
	}

	override fun onSignUpFailure(errorCode: Int) {
		btnSignUp.buttonStatus = StylingFlowButtonStatus.STATUS_ENABLED.status
		val errorMessageResource: Int = when (errorCode) {
			501 -> R.string.error_malformed_email_sign_up
			502 -> R.string.error_user_already_exists_sign_up
			503 -> R.string.error_username_not_available_sign_up
			504 -> R.string.error_invalid_birth_date_sign_up
			505 -> R.string.error_invalid_phone_sign_up
			else -> -1
		}
		listener?.showSignUpError(errorMessageResource)
	}

	override fun onEmptyCredentialsError() {
		btnSignUp.buttonStatus = StylingFlowButtonStatus.STATUS_ENABLED.status
		listener?.showSignUpError(R.string.error_empty_credentials_sign_up)
	}

}