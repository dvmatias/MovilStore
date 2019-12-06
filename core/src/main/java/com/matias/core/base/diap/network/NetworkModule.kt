package com.matias.core.base.diap.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.matias.core.R
import com.matias.data.platform.NetworkHandler
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

	companion object {
		private const val CONNECT_TIMEOUT_VALUE = 45L
		private const val READ_TIMEOUT_VALUE = 45L
		private const val WRITE_TIMEOUT_VALUE = 45L
	}

	@Provides
	@Singleton
	fun provideRetrofit(context: Context, gson: Gson): Retrofit =
			Retrofit.Builder()
					.baseUrl("") // TODO use from flavours INVESTIGATE!!!!!
					.addConverterFactory(GsonConverterFactory.create(gson))
					.client(createRetrofitClient())
					.build()

	private fun createRetrofitClient(): OkHttpClient {
		val interceptor = HttpLoggingInterceptor()
		interceptor.level = HttpLoggingInterceptor.Level.BODY
		val okHttpClient = OkHttpClient.Builder()
				.connectTimeout(CONNECT_TIMEOUT_VALUE, TimeUnit.SECONDS)
				.readTimeout(READ_TIMEOUT_VALUE, TimeUnit.SECONDS)
				.writeTimeout(WRITE_TIMEOUT_VALUE, TimeUnit.SECONDS)
				.addInterceptor(interceptor)
		return okHttpClient.build()
	}

	@Singleton
	@Provides
	fun provideGson(): Gson = GsonBuilder().disableHtmlEscaping().create()

	@Singleton
	@Provides
	fun provideNetworkHandler(context: Context): NetworkHandler = NetworkHandler(context)

}