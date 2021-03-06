package com.matias.data.provider

import android.util.Log
import com.matias.data.handlers.ApiHerrorHandler
import com.matias.data.platform.NetworkHandler
import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import retrofit2.Call

open class NetworkProvider(protected val networkHandler: NetworkHandler) {

	open fun <T, R> request(call: Call<T>, transform: (T) -> R): Either<FailureType, R> {
		return try {
			when (networkHandler.isConnected) {
				true -> {
					val response = call.execute()
					when (response.isSuccessful) {
						true -> {
							response.body()?.let {
								Either.Right(transform(it))
							} ?: Either.Left(FailureType.LocalErrorType(response.code()))
						}
						false -> Either.Left(ApiHerrorHandler.handleError(response))
					}
				}
				false, null -> Either.Left(FailureType.NetworkConnection)
			}
		} catch (e: Exception) {
			Log.e(NetworkProvider::class.java.simpleName, "$e")
			Either.Left(FailureType.ServerError)
		}
	}

}