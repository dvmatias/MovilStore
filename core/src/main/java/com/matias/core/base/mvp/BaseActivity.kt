package com.matias.core.base.mvp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.matias.core.base.diap.base.ViewComponent
import com.matias.core.navigator.Navigator
import javax.inject.Inject

val ROOT: Nothing? = null

abstract class BaseActivity<
        in V : BaseContract.View,
        out C : ViewComponent<V>> : AppCompatActivity() {
    
    @Inject
    protected lateinit var navigator: Navigator
    
    protected abstract fun bindComponent(): C
    
    protected abstract fun bindLayout(): Int
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        bindComponent().inject(this as V)
        setContentView(layoutInflater.inflate(bindLayout(), ROOT))
    }
    
    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    fun applyImmersiveFullScreen() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        window.decorView.setOnSystemUiVisibilityChangeListener {
            checkSystemUiVisibilityFullScreen(it)
        }
    }
    
    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
    
    private fun checkSystemUiVisibilityFullScreen(visibility: Int) {
        if (visibility == View.VISIBLE)
            Handler().postDelayed(
                    {
                        applyImmersiveFullScreen()
                    }, 200
            )
    }
    
    /**
     * Draw full screen with navitgation bar.
     */
    fun applyFullScreen() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )
        window.decorView.setOnSystemUiVisibilityChangeListener {
            checkSystemUiVisibilityFullScreen(it)
        }
    }
    
}