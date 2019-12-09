package com.matias.data.mappers.splash

import com.matias.data.entities.splash.GlobalConfigResponseEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.splash.GlobalConfigModel

const val SPLASH_ANIMATION_TYPE = "ONE"

class GlobalConfigMapper : Mapper<GlobalConfigResponseEntity, GlobalConfigModel>() {

    override fun transformEntityToModel(e: GlobalConfigResponseEntity): GlobalConfigModel {
        val splashAnimationType: String = e.splashAnimationType ?: SPLASH_ANIMATION_TYPE

        return GlobalConfigModel(splashAnimationType)
    }

}