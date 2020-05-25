package com.matias.core.navigator

import android.app.Activity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat

interface Navigator {

	fun toMainScreen(activityOrigin: Activity, bundle: Bundle?, options: ActivityOptionsCompat?, finish: Boolean)

	fun toLoginScreen(activityOrigin: Activity, bundle: Bundle?, options: ActivityOptionsCompat?, finish: Boolean)

	fun toProducDetailsScreen(activityOrigin: Activity, bundle: Bundle?, options: ActivityOptionsCompat?, finish: Boolean)

}