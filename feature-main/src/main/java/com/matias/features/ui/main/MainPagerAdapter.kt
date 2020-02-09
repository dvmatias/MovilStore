package com.matias.features.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainPagerAdapter(fm: FragmentManager, behavior: Int) :
	FragmentStatePagerAdapter(fm, behavior) {

	private val fragmentList = ArrayList<Fragment>()

	init {
		fragmentList.add(Fragment())
		fragmentList.add(Fragment())
		fragmentList.add(Fragment())
		fragmentList.add(Fragment())
	}

	override fun getItem(position: Int): Fragment = fragmentList[position]

	override fun getCount(): Int = fragmentList.size

}