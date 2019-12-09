package com.matias.core.base.diap.base

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.matias.core.base.diap.coroutineDispatcher.CoroutineModule
import com.matias.core.base.diap.network.NetworkModule
import com.matias.core.base.diap.storage.StorageModule
import com.matias.data.cache.featureflag.FeatureFlaghCache
import com.matias.data.platform.NetworkHandler
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [
    BaseModule::class,
    NetworkModule::class,
    StorageModule::class,
    CoroutineModule::class
])
interface BaseComponent {

    fun application(): Application

    fun context(): Context

    fun gson(): Gson

    fun networkHandler(): NetworkHandler

    fun featureFlaghCache(): FeatureFlaghCache

    fun retrofit(): Retrofit

}