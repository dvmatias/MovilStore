package com.matias.features.ui.main.fragments.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.matias.domain.models.novelty.NoveltyModel

class AdapterPagerNovelty(fm: FragmentManager) : FragmentPagerAdapter(fm) {

	private lateinit var noveltyList: ArrayList<NoveltyModel>

	fun setData(noveltyList: ArrayList<NoveltyModel>) {
		if (noveltyList.isEmpty()) {
			return
		}
		noveltyList.map { this.noveltyList }
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
	override fun getItem(position: Int): Fragment =
		getFragmentBasedOnPosition(position)

	private fun getFragmentBasedOnPosition(position: Int): Fragment {
		// TODO
		return Fragment()
	}

}

/*
public static class MyAdapter extends FragmentPagerAdapter {
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Fragment getItem(int position) {
        return getFragmentBasedOnPosition(position);
    }

    private Fragment getFragmentBasedOnPosition(int position) {
        int fragmentPos = position % 3; // Assuming you have 3 fragments
        switch(fragmentPos) {
            case 1:
            return Fragment1.newInstance();
            case 2:
            return Fragment2.newInstance();
            case 3:
            return Fragment3.newInstance();
        }
    }
}
 */