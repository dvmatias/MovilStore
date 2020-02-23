package com.matias.features.ui.main.fragments.shopcart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matias.features.R

class MainShopCartFragment : Fragment() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let { }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
		inflater.inflate(R.layout.fragment_main_shop_cart, container, false)

	companion object {

		@JvmStatic
		fun newInstance(): MainShopCartFragment =
			MainShopCartFragment().apply {
				arguments = Bundle().apply { }
			}
	}
}
