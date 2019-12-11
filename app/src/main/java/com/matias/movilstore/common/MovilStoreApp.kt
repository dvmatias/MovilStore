package com.matias.movilstore.common

import com.matias.core.base.BaseApplication
import com.matias.core.navigator.Navigator
import com.matias.movilstore.common.navigator.NavigatorImpl

class MovilStoreApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun bindNavigator(): Navigator =
            NavigatorImpl()
}