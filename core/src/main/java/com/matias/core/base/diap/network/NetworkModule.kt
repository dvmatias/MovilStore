package com.matias.core.base.diap.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

	@Singleton
	@Provides
	fun provideGson(): Gson = GsonBuilder().disableHtmlEscaping().create()

}