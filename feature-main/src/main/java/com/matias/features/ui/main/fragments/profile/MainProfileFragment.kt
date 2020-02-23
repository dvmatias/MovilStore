package com.matias.features.ui.main.fragments.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matias.features.R

class MainProfileFragment : Fragment() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let { }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
		inflater.inflate(R.layout.fragment_main_profile, container, false)

	companion object {

		@JvmStatic
		fun newInstance(): MainProfileFragment =
			MainProfileFragment().apply {
				arguments = Bundle().apply { }
			}
	}

}
