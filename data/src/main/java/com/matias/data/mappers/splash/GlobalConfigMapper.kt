package com.matias.data.mappers.splash

import com.matias.data.entities.splash.GlobalConfigResponseEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.splash.GlobalConfigResponseModel

const val SPLASH_ANIMATION_TYPE = "ONE"

class GlobalConfigMapper : Mapper<GlobalConfigResponseEntity, GlobalConfigResponseModel>() {

    override fun transformEntityToModel(e: GlobalConfigResponseEntity): GlobalConfigResponseModel {
        val splashAnimationType: String = e.splashAnimationType ?: SPLASH_ANIMATION_TYPE

        return GlobalConfigResponseModel(splashAnimationType)
    }

}