package com.matias.features.login.ui.fragments.signin

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matias.core.base.mvp.BasePresenterFragment
import com.matias.features.R
import com.matias.features.login.di.fragments.signin.SignInFragmentModule
import com.matias.features.login.di.fragments.signin.SignInFragmentSubcomponent
import com.matias.features.login.ui.LoginUiComponent

class SignInFragment :
	BasePresenterFragment<SignInFragment, SignInFragmentPresenter, SignInFragmentSubcomponent>(),
	SignInFragmentContract.View {

	override fun bindComponent(): SignInFragmentSubcomponent =
		LoginUiComponent.component.plus(SignInFragmentModule())

	override fun bindLayout(): Int =
		R.layout.fragment_sign_in

	private var listener: OnFragmentInteractionListener? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_sign_in, container, false)

	fun onButtonPressed(uri: Uri) {
		listener?.onFragmentInteraction(uri)
	}

	override fun onDetach() {
		super.onDetach()
		listener = null
	}

	interface OnFragmentInteractionListener {
		fun onFragmentInteraction(uri: Uri)
	}

	companion object {
		@JvmStatic
		fun newInstance(): SignInFragment =
			SignInFragment()
	}

}