package com.matias.features.ui.main.fragments.home.novelty

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.gson.Gson
import com.matias.domain.models.novelty.NoveltyModel

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
		Int.MAX_VALUE

	/**
	 * Return the Fragment associated with a specified position.
	 */
	override fun getItem(position: Int): ItemNoveltyFragment =
		getFragmentBasedOnPosition(position)

	/**
	 *
	 */
	private fun getFragmentBasedOnPosition(position: Int): ItemNoveltyFragment {
		val fragmentPosition = position % noveltyList.size
		return ItemNoveltyFragment().newInstance(Gson().toJson(noveltyList[fragmentPosition], NoveltyModel::class.java))
	}

}