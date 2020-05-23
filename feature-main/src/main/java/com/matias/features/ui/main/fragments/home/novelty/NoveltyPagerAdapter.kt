package com.matias.features.ui.main.fragments.home.novelty

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.gson.Gson
import com.matias.domain.models.novelty.NoveltyModel

private const val COUNT = 1000

@Suppress("DEPRECATION")
class NoveltyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

	private var noveltyList: List<NoveltyModel> = listOf()

	fun setData(noveltyList: List<NoveltyModel>) {
		if (noveltyList.isEmpty()) return

		this.noveltyList = noveltyList.filter { it.imageUrl.isNotEmpty() }

		notifyDataSetChanged()
	}

	/**
	 * Return the number of views available.
	 */
	override fun getCount(): Int =
		COUNT

	/**
	 * Return the Fragment associated with a specified position.
	 */
	override fun getItem(position: Int): Fragment =
		ItemNoveltyFragment()
			.newInstance(
				Gson().toJson(
					noveltyList[position % noveltyList.size],
					NoveltyModel::class.java
				)
			)

}