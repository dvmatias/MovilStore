package com.matias.features.login.ui.login

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.features.R
import com.matias.features.login.di.login.LoginActivityModule
import com.matias.features.login.di.login.LoginActivitySubComponent
import com.matias.features.login.ui.LoginUiComponent

class LoginActivity : BasePresenterActivity<LoginActivity, LoginActivityPresenter, LoginActivitySubComponent>(), LoginActivityContract.View {
    
    override fun bindComponent(): LoginActivitySubComponent =
            LoginUiComponent.component.plus(LoginActivityModule())
    
    override fun bindLayout(): Int =
            R.layout.activity_login
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        super.applyFullScreen()
    }
    
}
