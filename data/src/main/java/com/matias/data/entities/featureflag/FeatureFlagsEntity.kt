package com.matias.data.entities.featureflag

import com.google.gson.annotations.SerializedName

data class FeatureFlagsEntity(
        @SerializedName("mob_flag_main_scren_home_tab_enable") val mainScrenHomeTabEnable: Boolean?,
        @SerializedName("mob_flag_main_scren_products_tab_enable") val mainScrenEcommerceTabEnable: Boolean?,
        @SerializedName("mob_flag_main_scren_contact_us_tab_enable") val mainScrenContactUsTabEnable: Boolean?
)