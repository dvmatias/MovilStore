package com.matias.features.login.ui.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.matias.features.login.ui.fragments.signin.SignInFragment
import com.matias.features.login.ui.fragments.signup.SignUpFragment

private const val ITEM_COUNT = 2

private const val POSITION_SIGN_IN = 0
private const val POSITION_SIGN_UP = 1

class LoginPagerAdapter(fm: FragmentManager, behavior: Int) :
	FragmentStatePagerAdapter(fm, behavior) {

	private val fragmentList = ArrayList<Fragment>()

	enum class PAGE(val position: Int) {
		SIGN_IN(POSITION_SIGN_IN),
		SIGN_UP(POSITION_SIGN_UP)
	}

	init {
		fragmentList.add(SignInFragment.newInstance())
		fragmentList.add(SignUpFragment.newInstance())
	}

	override fun getItem(position: Int): Fragment = fragmentList[position]

	override fun getCount(): Int = ITEM_COUNT

}