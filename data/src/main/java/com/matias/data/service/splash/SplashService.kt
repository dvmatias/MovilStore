package com.matias.data.service.splash

import com.matias.data.entities.splash.GlobalConfigResponseEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class SplashService constructor(retrofit: Retrofit) : SplashApi {
    
    private val splashApi: SplashApi by lazy { retrofit.create(SplashApi::class.java) }
    
    override fun fetchGlobalConfig(): Call<GlobalConfigResponseEntity> = splashApi.fetchGlobalConfig()
    
}