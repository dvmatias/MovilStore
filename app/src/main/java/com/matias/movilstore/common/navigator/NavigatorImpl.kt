package com.matias.movilstore.common.navigator

import android.app.Activity
import com.matias.core.extensions.navigateTo
import com.matias.core.navigator.Navigator
import com.matias.features.login.ui.login.LoginActivity
import com.matias.movilstore.MainActivity

class NavigatorImpl : Navigator {

    override fun toMainScreen(activityOrigin: Activity) {
        activityOrigin.navigateTo<MainActivity>(null)
    }

    override fun toLoginScreen(activityOrigin: Activity) {
        activityOrigin.navigateTo<LoginActivity>(null)
    }

}