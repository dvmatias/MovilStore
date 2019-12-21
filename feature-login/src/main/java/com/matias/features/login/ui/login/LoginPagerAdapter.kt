package com.matias.features.login.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.matias.features.login.ui.fragments.signin.SignInFragment
import com.matias.features.login.ui.fragments.signup.SignUpFragment


class LoginPagerAdapter(fm: FragmentManager, behavior: Int) :
	FragmentStatePagerAdapter(fm, behavior) {

	private val fragmentList = ArrayList<Fragment>()

	enum class PAGE {
		SIGN_IN,
		SIGN_UP
	}

	init {
		fragmentList.add(SignInFragment.newInstance())
		fragmentList.add(SignUpFragment.newInstance())
	}

	override fun getItem(position: Int): Fragment = fragmentList[position]

	override fun getCount(): Int = 2

}