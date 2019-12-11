package com.matias.data.provider

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
                            } ?: Either.Left(FailureType.ServerError(response.code()))
                        }
                        false -> Either.Left(FailureType.ServerError(response.code()))
                    }
                }
                false, null -> Either.Left(FailureType.NetworkConnection)
            }
        } catch (e: Exception) {
            Either.Left(FailureType.ServerError())
        }
    }

    // TODO

}