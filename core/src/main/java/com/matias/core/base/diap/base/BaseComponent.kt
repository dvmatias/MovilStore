package com.matias.core.base.diap.base

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.matias.core.base.diap.network.NetworkModule
import com.matias.core.base.diap.storage.StorageModule
import com.matias.data.cache.splash.SplashCache
import com.matias.data.platform.NetworkHandler
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    BaseModule::class,
    NetworkModule::class,
    StorageModule::class
])
interface BaseComponent {

    fun application(): Application

    fun context(): Context

    fun gson(): Gson

    fun networkHandler(): NetworkHandler

    fun splashCache(): SplashCache

}