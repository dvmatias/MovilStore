package com.matias.data.service.main

import com.matias.data.entities.mainposition.MainPositionEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class MainPositionService constructor(retrofit: Retrofit) : MainPositionApi {

	private val mainPositionApi by lazy { retrofit.create(MainPositionApi::class.java) }

	override fun getMainPosition(): Call<MainPositionEntity> =
		mainPositionApi.getMainPosition()

}