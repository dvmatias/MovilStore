package com.matias.movilstore.common.navigator

import android.app.Activity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.matias.core.extensions.navigateTo
import com.matias.core.navigator.Navigator
import com.matias.features.login.ui.login.LoginActivity
import com.matias.features.ui.main.activity.MainActivity
import com.matias.features.ui.productdetails.activity.ProductDetailsActivity

class NavigatorImpl : Navigator {

	override fun toMainScreen(activityOrigin: Activity, bundle: Bundle?, options: ActivityOptionsCompat?, finish: Boolean) {
		activityOrigin.navigateTo<MainActivity>(bundle, options, finish)
	}

	override fun toLoginScreen(activityOrigin: Activity, bundle: Bundle?, options: ActivityOptionsCompat?, finish: Boolean) {
		activityOrigin.navigateTo<LoginActivity>(bundle, options, finish)
	}

	override fun toProducDetailsScreen(activityOrigin: Activity, bundle: Bundle?, options: ActivityOptionsCompat?, finish: Boolean) {
		activityOrigin.navigateTo<ProductDetailsActivity>(bundle, options, finish)
	}

}