package com.matias.domain.models

data class ApiErrorModel(
	val errorType: Int,
	val errorMessage: String,
	val errorCode: Int
)