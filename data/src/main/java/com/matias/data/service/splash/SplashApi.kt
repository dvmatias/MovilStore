package com.matias.data.service.splash

import retrofit2.Call
import retrofit2.http.GET

interface SplashApi {

    @GET("")
    fun doSomethingOnServer(): Call<Any>

}