package com.matias.core.base.diap.base

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.matias.core.base.diap.coroutineDispatcher.CoroutineModule
import com.matias.core.base.diap.helpers.HelpersModule
import com.matias.core.base.diap.network.NetworkModule
import com.matias.core.base.diap.storage.StorageModule
import com.matias.core.navigator.Navigator
import com.matias.data.cache.featureflag.FeatureFlaghCache
import com.matias.data.cache.loginstatus.SharedPreferencesCache
import com.matias.data.cache.mainposition.MainPositionCache
import com.matias.data.platform.NetworkHandler
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		BaseModule::class,
		NetworkModule::class,
		HelpersModule::class,
		StorageModule::class,
		CoroutineModule::class
	]
)
interface BaseComponent {

	fun application(): Application

	fun context(): Context

	fun gson(): Gson

	fun retrofit(): Retrofit

	fun networkHandler(): NetworkHandler

	fun navigator(): Navigator

	fun featureFlaghCache(): FeatureFlaghCache

	fun sharedPreferencesCache(): SharedPreferencesCache

	fun mainPositionCache(): MainPositionCache

}