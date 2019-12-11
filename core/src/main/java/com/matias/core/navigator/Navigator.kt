package com.matias.core.navigator

import android.app.Activity

interface Navigator {

    fun toMainScreen(activityOrigin: Activity)

    fun toLoginScreen(activityOrigin: Activity)

}