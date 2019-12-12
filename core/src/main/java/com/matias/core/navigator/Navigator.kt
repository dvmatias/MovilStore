package com.matias.core.navigator

import android.app.Activity
import androidx.core.app.ActivityOptionsCompat

interface Navigator {
    
    fun toMainScreen(activityOrigin: Activity, options: ActivityOptionsCompat?)
    
    fun toLoginScreen(activityOrigin: Activity, options: ActivityOptionsCompat?)
    
}