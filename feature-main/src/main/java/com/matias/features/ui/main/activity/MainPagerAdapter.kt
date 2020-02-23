package com.matias.features.ui.main.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.matias.features.ui.main.fragments.contactus.MainContactUsFragment
import com.matias.features.ui.main.fragments.home.MainHomeFragment
import com.matias.features.ui.main.fragments.products.MainProductsFragment
import com.matias.features.ui.main.fragments.profile.MainProfileFragment
import com.matias.features.ui.main.fragments.shopcart.MainShopCartFragment

class MainPagerAdapter(fm: FragmentManager, behavior: Int) :
	FragmentStatePagerAdapter(fm, behavior) {

	private val fragmentList = ArrayList<Fragment>()

	init {
		fragmentList.add(MainHomeFragment.newInstance())
		fragmentList.add(MainProductsFragment.newInstance())
		fragmentList.add(MainShopCartFragment.newInstance())
		fragmentList.add(MainContactUsFragment.newInstance())
		fragmentList.add(MainProfileFragment.newInstance())
	}

	override fun getItem(position: Int): Fragment = fragmentList[position]

	override fun getCount(): Int = fragmentList.size

}