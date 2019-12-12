package com.matias.movilstore.common.navigator

import android.app.Activity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.matias.core.extensions.navigateTo
import com.matias.core.navigator.Navigator
import com.matias.features.login.ui.login.LoginActivity
import com.matias.movilstore.MainActivity

class NavigatorImpl : Navigator {

    override fun toMainScreen(activityOrigin: Activity, options: ActivityOptionsCompat?) {
        activityOrigin.navigateTo<MainActivity>(null, options)
    }

    override fun toLoginScreen(activityOrigin: Activity, options: ActivityOptionsCompat?) {
        activityOrigin.navigateTo<LoginActivity>(null, options)
    }

}