package com.matias.features.login.ui.login

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.core.helpers.HtmlHelper
import com.matias.domain.models.user.UserModel
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

	@Inject
	lateinit var htmlHelper: HtmlHelper

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

	override fun showSignInError(errorMsg: String) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun goToMainScreen() {
		super.showToast("go to main screen")
	}

	/**
	 * [LoginActivityContract.SignInFragmentInteractionListener] implementation
	 */

	override fun showErrorBadCredentials(errorMessageResource: Int) {
		super.showToast("show error bad crecentials")
	}

	override fun showMessagePasswordSent() {
		super.showToast("show message password sent")
	}

	override fun onSignInSuccess(userModel: UserModel) {
		super.showToast("onSignInSuccess :: $userModel")
		// TODO() mannage 'stay signed in' option.
		// TODO() Hide loading
		goToMainScreen()
	}

	override fun showEmptyCredentialsError() {
		bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet)
		tvBottomSheetErrorMessage.text = htmlHelper.fromHtml(R.string.error_empty_credentials_sign_in)
		bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
	}

	override fun hideError() {
		bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
	}

}