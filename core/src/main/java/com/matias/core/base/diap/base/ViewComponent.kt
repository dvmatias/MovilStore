package com.matias.core.base.diap.base

import com.matias.core.base.mvp.BaseContract

interface ViewComponent<in V : BaseContract.View> {
    
    fun inject(view: V)
    
}