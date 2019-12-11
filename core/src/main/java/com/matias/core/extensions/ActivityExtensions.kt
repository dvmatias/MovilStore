package com.matias.core.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat

inline fun <reified T : Activity> Activity.navigateTo(bundle: Bundle?) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(bundle)
    }
    overridePendingTransition(0, 0)
    ActivityCompat.startActivity(this, intent, null)
}