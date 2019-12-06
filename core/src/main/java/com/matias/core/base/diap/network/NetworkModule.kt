package com.matias.core.base.diap.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.matias.data.platform.NetworkHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

	@Singleton
	@Provides
	fun provideGson(): Gson = GsonBuilder().disableHtmlEscaping().create()

	@Singleton
	@Provides
	fun provideNetworkHandler(context: Context): NetworkHandler = NetworkHandler(context)

}