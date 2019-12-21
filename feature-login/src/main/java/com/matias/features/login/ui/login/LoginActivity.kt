package com.matias.features.login.ui.login

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.features.R
import com.matias.features.login.di.login.LoginActivityModule
import com.matias.features.login.di.login.LoginActivitySubComponent
import com.matias.features.login.ui.LoginUiComponent
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bottom_sheet_error.*
import javax.inject.Inject


class LoginActivity :
	BasePresenterActivity<LoginActivity, LoginActivityPresenter, LoginActivitySubComponent>(),
	LoginActivityContract.View, LoginActivityContract.SignInFragmentInteractionListener {

	@Inject
	lateinit var pagerAdapter: LoginPagerAdapter

	private lateinit var tabTitles: List<String>

	private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

	override fun bindComponent(): LoginActivitySubComponent =
		LoginUiComponent.component.plus(LoginActivityModule(this))

	override fun bindLayout(): Int = R.layout.activity_login

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		super.applyFullScreen()

		setupPager()
		setupTabLayout()
		bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet)
	}

	private fun setupPager() {
		viewPager.adapter = pagerAdapter
	}

	private fun setupTabLayout() {
		tabTitles =
			listOf(getString(R.string.tab_sign_in_label), getString(R.string.tab_sign_up_label))
		tabs.setupWithViewPager(viewPager)
		tabs.setTitlesAtTabs(tabTitles)
	}

	/**
	 * [LoginActivityContract.View] implementation
	 */

	override fun showEmptyCredentialsError(show: Boolean) {
		bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet)
//		tvBottomSheetErrorMessage.text = htmlHelper.fromHtml(stringResource)
		bottomSheetBehavior.state =
			if (show) BottomSheetBehavior.STATE_EXPANDED else BottomSheetBehavior.STATE_COLLAPSED
	}

	override fun showSignInError(errorMsg: String) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun goToMainScreen() {
		super.showToast("go to main screen")
	}

	/**
	 * [LoginActivityContract.SignInFragmentInteractionListener] implementation
	 */

	override fun onUserClickLoginWithFacebook() {
		super.showToast("user click login with facebook")
	}

	override fun onUserClickLoginWithGoogle() {
		super.showToast("user click login with google")
	}

	override fun onUserClickForgotPassword() {
		super.showToast("user click forgot pass")
	}

	override fun onUserClickDontHaveAccount() {
		super.showToast("user click don't have account")
	}

	override fun onUserClickSignIn(usernName: String?, password: String?) {
		presenter.signIn(usernName, password)
	}

	override fun showErrorBadCredentials() {
		super.showToast("show error bad crecentials")
	}

	override fun showMessagePasswordSent() {
		super.showToast("show message password sent")
	}

	override fun hideEmptyCredentialsError() {
		showEmptyCredentialsError(false)
	}
}