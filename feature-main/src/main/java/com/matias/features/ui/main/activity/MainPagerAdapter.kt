package com.matias.features.ui.main.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.matias.domain.models.item.ItemMainPageModel

class MainPagerAdapter(itemMainPageList: MutableList<ItemMainPageModel>, fm: FragmentManager, behavior: Int) :
	FragmentStatePagerAdapter(fm, behavior) {

	private val fragmentList = ArrayList<Fragment>()

	init {
		for (itemMainPage in itemMainPageList) {
			if (itemMainPage.enable) {
				fragmentList.add(itemMainPage.fragment)
			}
		}
	}

	override fun getItem(position: Int): Fragment = fragmentList[position]

	override fun getCount(): Int = fragmentList.size

}