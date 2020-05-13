package com.matias.data.service.main

import com.matias.data.entities.mainposition.MainPositionEntity
import retrofit2.Call
import retrofit2.http.GET

interface MainPositionApi {

	companion object {
		private const val CONTROLLER_MAIN_POSITION = "main-position"

		private const val EP_MAIN_POSITION = "$CONTROLLER_MAIN_POSITION/main"
	}

	@GET(EP_MAIN_POSITION)
	fun getMainPosition(): Call<MainPositionEntity>

}