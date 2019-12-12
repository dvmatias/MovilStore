package com.matias.features.login.ui.login

import android.os.Bundle
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.features.R
import com.matias.features.login.di.login.LoginActivityModule
import com.matias.features.login.di.login.LoginActivitySubComponent
import com.matias.features.login.ui.LoginUiComponent
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BasePresenterActivity<LoginActivity, LoginActivityPresenter, LoginActivitySubComponent>(), LoginActivityContract.View {
    
    override fun bindComponent(): LoginActivitySubComponent =
            LoginUiComponent.component.plus(LoginActivityModule())
    
    override fun bindLayout(): Int =
            R.layout.activity_login
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        super.applyFullScreen()
        
        setupPager()
        setupTabLayout()
    }
    
    private fun setupPager() {
        // TODO
    }
    
    private fun setupTabLayout() {
        tabs.addTab(tabs.newTab().setText("SIGN IN"))
        tabs.addTab(tabs.newTab().setText("SIGN UP"))
        tabs.setupWithViewPager(viewPager)
        tabs.setTitlesAtTabs(listOf("ABC", "DEF"))
    }
    
}