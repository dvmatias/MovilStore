package com.matias.data.handlers

import com.google.gson.Gson
import com.matias.data.entities.ApiErrorEntity
import com.matias.domain.base.exception.FailureType
import com.matias.domain.models.ApiErrorModel
import org.json.JSONObject
import retrofit2.Response

class ApiHerrorHandler {

	companion object {

		fun handleError(response: Response<*>): FailureType =
			try {
				val apiErrorEntity = Gson().fromJson(JSONObject(response.errorBody()!!.string()).toString(), ApiErrorEntity::class.java)
				apiErrorEntity.let {
					getErrorClass(ApiErrorModel(it.errorType ?: -1, it.errorMessage ?: "", it.errorCode ?: -1))
				}
			} catch (e: java.lang.Exception) {
				FailureType.ServerError
			}

		private fun getErrorClass(apiError: ApiErrorModel): FailureType =
			when (apiError.errorType) {
				841 -> {
					when (apiError.errorCode) {
						// TODO: Replace for constants.
						501 -> FailureType.SignUpErrorType.MalformedEmailServerException(apiError)
						502 -> FailureType.SignUpErrorType.UserAlreadyExistsServerException(apiError)
						503 -> FailureType.SignUpErrorType.UserNameNotAvailableServerException(apiError)
						504 -> FailureType.SignUpErrorType.InvalidBirthDayServerException(apiError)
						505 -> FailureType.SignUpErrorType.InvalidPhoneServerException(apiError)
						else -> FailureType.ServerError
					}
				}
				else -> FailureType.ServerError
			}
	}

}