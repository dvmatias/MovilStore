package com.matias.features.ui.main.fragments.home.novelty

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import com.matias.core.managers.GlideApp
import com.matias.domain.models.novelty.NoveltyModel
import com.matias.features.R
import kotlinx.android.synthetic.main.fragment_item_novelty.*

private const val ARG_PARAM_NOVELTY = "novelty"

class ItemNoveltyFragment : Fragment() {

	fun newInstance(novelty: String): ItemNoveltyFragment =
		ItemNoveltyFragment().apply {
			arguments = Bundle().apply {
				putString(ARG_PARAM_NOVELTY, novelty)
			}
		}

	private var novelty: NoveltyModel? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let {
			val noveltyString = it.getString(ARG_PARAM_NOVELTY)
			novelty = Gson().fromJson<NoveltyModel>(noveltyString, NoveltyModel::class.java)
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
		inflater.inflate(R.layout.fragment_item_novelty, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		novelty?.let { novelty: NoveltyModel ->
			cv.setCardBackgroundColor(Color.parseColor(novelty.backgroundColor))
			activity?.let { activity: FragmentActivity ->
				GlideApp.with(activity)
					.load(novelty.imageUrl)
					.centerCrop()
					.into(iv)
			}

		}

	}

}