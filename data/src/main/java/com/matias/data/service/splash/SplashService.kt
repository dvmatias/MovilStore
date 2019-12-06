package com.matias.data.service.splash

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class SplashService constructor() : SplashApi {

//    private val splashApi: SplashApi by lazy { retrofit.create(SplashApi::class.java) }

    override fun doSomethingOnServer(): Call<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}