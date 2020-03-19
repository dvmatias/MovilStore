package com.matias.features.login.ui.login

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.core.helpers.HtmlHelper
import com.matias.core.helpers.pxToDp
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
	LoginActivityContract.View, LoginActivityContract.FragmentInteractionListener {

	companion object {
		private const val HIDE_SIGN_IN_ERROR_DELAY = 5000L
	}

	@Inject
	lateinit var pagerAdapter: LoginPagerAdapter

	@Inject
	lateinit var htmlHelper: HtmlHelper

	private lateinit var tabTitles: List<String>

	private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

	override fun bindComponent(): LoginActivitySubComponent =
		LoginUiComponent.component.plus(LoginActivityModule(this))

	override fun bindLayout(): Int =
		R.layout.activity_login

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		super.applyFullScreen()

		setupPager()
		setupTabLayout()
		setupLoadingView()
		bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet)
	}

	private fun setupPager() {
		pager.adapter = pagerAdapter
	}

	private fun setupTabLayout() {
		tabTitles =
			listOf(getString(R.string.tab_sign_in_label), getString(R.string.tab_sign_up_label))
		tabs.setupWithViewPager(pager)
		tabs.setTitlesAtTabs(tabTitles)
	}

	private fun setupLoadingView() {
		viewLoading.setOnApplyWindowInsetsListener { v, insets ->
			(v.layoutParams as CoordinatorLayout.LayoutParams).topMargin = insets.systemWindowInsetTop
			insets
		}
	}

	override fun onStop() {
		super.onStop()
		finish()
	}

	override fun onBackPressed() {
		when (LoginPagerAdapter.PAGE.SIGN_IN.position != pager.currentItem) {
			true -> pager.currentItem = LoginPagerAdapter.PAGE.SIGN_IN.position
			else -> finish()
		}
	}

	/*******************************************************************************************************************
	 * [LoginActivityContract.View] implementation
	 */

	override fun showSignInError(errorMsgResource: Int) {
		clBottomSheet.requestFocus()
		bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet)
		tvBottomSheetErrorMessage.text = htmlHelper.fromHtml(errorMsgResource)
		bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
		Handler().postDelayed({ hideSignInError() }, HIDE_SIGN_IN_ERROR_DELAY)
	}

	override fun showSignUpErrorFields(errorMsgResource: Int) {
		clBottomSheet.requestFocus()
		bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet)
		tvBottomSheetErrorMessage.text = htmlHelper.fromHtml(errorMsgResource)
		bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
		Handler().postDelayed({ hideSignInError() }, HIDE_SIGN_IN_ERROR_DELAY)
	}

	override fun hideSignInError() {
		clBottomSheet.clearFocus()
		bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
	}

	override fun goToMainScreen() {
        navigator.toMainScreen(this, null, null, true)
	}

	override fun showLoading(show: Boolean) {
		viewLoading.apply {
			visibility = if (show) View.VISIBLE else View.GONE
			requestFocus()
		}
	}

	override fun goToSignUpFragment() {
		pager.currentItem = LoginPagerAdapter.PAGE.SIGN_UP.position
	}

	/*******************************************************************************************************************
	 * [LoginActivityContract.FragmentInteractionListener] implementation
	 */

	override fun showErrorBadCredentials(errorMsgResource: Int) {
		showSignInError(errorMsgResource)
	}

	override fun showEmptyCredentialsError(errorMsgResource: Int) {
		showSignInError(errorMsgResource)
	}

	override fun showMessagePasswordSent() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onSignInSuccess(userModel: UserModel) {
		goToMainScreen()
	}

	override fun showSignUpError(errorMsgResource: Int) {
		showSignUpErrorFields(errorMsgResource)
	}

	override fun onSignUpSuccess(userModel: UserModel) {
		goToMainScreen()
	}

	override fun hideCredentialsError() {
		hideSignInError()
	}

}