package com.matias.data.service.splash

import com.matias.data.entities.splash.GlobalConfigResponseEntity
import retrofit2.Call
import retrofit2.http.GET

interface SplashApi {
    
    companion object {
        private const val CONTROLLER_CONFIGURATIONS = "configurations/"
        
        const val EP_GLOBAL_CONFIG = "${CONTROLLER_CONFIGURATIONS}global-config"
    }
    
    @GET(EP_GLOBAL_CONFIG)
    fun fetchGlobalConfig(): Call<GlobalConfigResponseEntity>
    
}