package com.matias.features

import android.content.Context
import androidx.fragment.app.Fragment
import com.matias.domain.models.featureflag.Features
import com.matias.domain.models.item.ItemMainPageModel
import com.matias.features.ui.main.fragments.contactus.MainContactUsFragment
import com.matias.features.ui.main.fragments.home.MainHomeFragment
import com.matias.features.ui.main.fragments.products.MainProductsFragment
import com.matias.features.ui.main.fragments.profile.MainProfileFragment
import com.matias.features.ui.main.fragments.shopcart.MainShopCartFragment

class MainItemPageHelper(context: Context) {

	private var itemMainPageList: MutableList<ItemMainPageModel> = mutableListOf()

	private var labelList: Array<String> =
		context.resources.getStringArray(R.array.styling_bottom_nav_labels)

	var tagList: List<Features> =
		listOf(
			Features.TAB_HOME,
			Features.TAB_PRODUCTS,
			Features.TAB_SHOP_CART,
			Features.TAB_CONTACT_US,
			Features.TAB_PROFILE
		)

	private var fragmentList: List<Fragment> =
		listOf(
			MainHomeFragment.newInstance(),
			MainProductsFragment.newInstance(),
			MainShopCartFragment.newInstance(),
			MainContactUsFragment.newInstance(),
			MainProfileFragment.newInstance()
		)

	private var iconList: IntArray =
		intArrayOf(
			R.drawable.ic_bottom_nav_home_32dp,
			R.drawable.ic_bottom_nav_products_32dp,
			R.drawable.ic_bottom_nav_shop_cart_32dp,
			R.drawable.ic_bottom_nav_contact_32dp,
			R.drawable.ic_bottom_nav_profile_32dp
		)

	fun getItemMainPageList(falgList: List<Boolean>): MutableList<ItemMainPageModel> {
		for (i in 0 until 5) {
			itemMainPageList.add(
				ItemMainPageModel(
					tagList[i],
					labelList[i],
					iconList[i],
					fragmentList[i],
					falgList[i]
				)
			)
		}
		return itemMainPageList
	}

}