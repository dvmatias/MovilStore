package com.matias.domain.models.item

import androidx.fragment.app.Fragment
import com.matias.domain.models.featureflag.Features

data class ItemMainPageModel(
	val tag: Features,
	val label: String,
	val icon: Int,
	val fragment: Fragment,
	val enable: Boolean
)
