package com.matias.features.login.ui.login

import com.matias.core.base.mvp.BasePresenter
import javax.inject.Inject

class LoginActivityPresenter @Inject constructor(

) : BasePresenter<LoginActivityContract.View>(), LoginActivityContract.Presenter<LoginActivityContract.View> {
	
	override fun loginWithFacebook() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun loginWithGoogle() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun signIn(usernName: String?, password: String?) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun handleSignInSuccess() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun handleSignInError() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
	override fun staySignedIn() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
	
}