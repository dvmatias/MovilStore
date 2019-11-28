package com.matias.core.base.mvp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.matias.core.base.diap.base.ViewComponent

val ROOT: Nothing? = null

abstract class BaseActivity<
		in V : BaseContract.View,
		out C : ViewComponent<V>> : AppCompatActivity() {

	protected abstract fun bindComponent(): C

	protected abstract fun bindLayout(): Int

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		@Suppress("UNCHECKED_CAST")
		bindComponent().inject(this as V)
		setContentView(layoutInflater.inflate(bindLayout(), ROOT))
	}

	override fun attachBaseContext(newBase: Context?) {
		super.attachBaseContext(newBase)
	}

	override fun onResume() {
		super.onResume()
	}

	override fun onPause() {
		super.onPause()
	}

	protected fun showToast(message: String) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
	}

}