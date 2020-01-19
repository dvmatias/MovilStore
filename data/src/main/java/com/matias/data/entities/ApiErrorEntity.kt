package com.matias.data.entities

import com.google.gson.annotations.SerializedName

data class ApiErrorEntity(
	@SerializedName("error_type") val errorType: Int?,
	@SerializedName("error_message") val errorMessage: String?,
	@SerializedName("error_code") val errorCode: Int?,
	@SerializedName("message") val message: String?,
	@SerializedName("status") val status: String?,
	@SerializedName("timestamp") val timestamp: String?
)