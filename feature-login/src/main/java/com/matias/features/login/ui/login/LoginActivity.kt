package com.matias.features.login.ui.login

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.matias.features.R

class LoginActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        // TODO Move to BaseActivity
        applyInmersive()
    }
    
    // TODO Move to BaseActivity (already present there)
    private fun checkSystemUiVisibilityFullScreen(visibility: Int) {
        if (visibility == View.VISIBLE)
            Handler().postDelayed(
                    {
                        applyInmersive()
                    }, 200
            )
    }
    
    // TODO Move to BaseActivity
    private fun applyInmersive() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
        window.decorView.setOnSystemUiVisibilityChangeListener {
            checkSystemUiVisibilityFullScreen(it)
        }
    }
    
}
