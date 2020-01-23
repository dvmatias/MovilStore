package com.matias.core.base.mvp

import android.os.Bundle
import com.matias.core.base.diap.base.ViewComponent
import javax.inject.Inject

abstract class BasePresenterActivity<
        in V : BaseContract.View,
        P : BaseContract.Presenter<V>,
        out C : ViewComponent<V>> : BaseActivity<V, C>() {
    
    @Inject
    protected lateinit var presenter: P
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V)
        presenter.create()
    }
    
    override fun onResume() {
        super.onResume()
        presenter.resume()
    }
    
    override fun onPause() {
        super.onPause()
        presenter.pause()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    
}