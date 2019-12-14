package com.matias.features.login.ui.login

import android.os.Bundle
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.features.R
import com.matias.features.login.di.login.LoginActivityModule
import com.matias.features.login.di.login.LoginActivitySubComponent
import com.matias.features.login.ui.LoginUiComponent
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity :
	BasePresenterActivity<LoginActivity, LoginActivityPresenter, LoginActivitySubComponent>(),
	LoginActivityContract.View {

	@Inject
	lateinit var pagerAdapter: LoginPagerAdapter

	private lateinit var tabTitles: List<String>

	override fun bindComponent(): LoginActivitySubComponent =
		LoginUiComponent.component.plus(LoginActivityModule(this))

	override fun bindLayout(): Int = R.layout.activity_login

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		super.applyFullScreen()

		setupPager()
		setupTabLayout()
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

}