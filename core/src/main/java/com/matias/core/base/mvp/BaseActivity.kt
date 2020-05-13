package com.matias.core.base.mvp

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.matias.core.base.diap.base.ViewComponent
import com.matias.core.navigator.Navigator
import javax.inject.Inject

val ROOT: Nothing? = null
private const val DELAY_TO_INMERSIION = 3000L

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

	/**
	 * Immersive full screen.
	 */
	fun applyImmersiveFullScreen() {
		hideSystemUI()
		window.decorView.setOnSystemUiVisibilityChangeListener {
			checkSystemUiVisibilityFullScreen(it)
		}
	}

	private fun hideSystemUI() {
		window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
				or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				or View.SYSTEM_UI_FLAG_FULLSCREEN)

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			val attrib = window.attributes
			attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
		}
	}

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
				}, DELAY_TO_INMERSIION
			)
	}

	/**
	 * Draw full screen with navitgation bar.
	 */
	fun applyFullScreen() {
		window.decorView.systemUiVisibility = (
				View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
						View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
	}

}