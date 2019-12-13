package com.matias.features.login.ui.login

import com.matias.core.base.mvp.BasePresenter
import javax.inject.Inject

class LoginActivityPresenter @Inject constructor(

) : BasePresenter<LoginActivityContract.View>(), LoginActivityContract.Presenter<LoginActivityContract.View>