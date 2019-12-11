package com.matias.domain.base.usecase

import com.matias.domain.base.exception.FailureType
import com.matias.domain.base.functional.Either
import kotlinx.coroutines.*

interface CoroutineDispatcherProvider {
    fun provideExecutor(): CoroutineDispatcher
    fun provideDispatcher(): CoroutineDispatcher
}

interface UseCaseInvocableContract<out T, in Params> : UseCaseContract {
    val dispatcherProvider: CoroutineDispatcherProvider
    operator fun invoke(onResult: (Either<FailureType, T>) -> Unit, params: Params)
}


abstract class UseCase<out T, in Params> : UseCaseInvocableContract<T, Params> where T : Any {

    private var job: Deferred<Either<FailureType, T>>? = null

    override val dispatcherProvider: CoroutineDispatcherProvider
        get() =
            object : CoroutineDispatcherProvider {
                override fun provideExecutor(): CoroutineDispatcher = Dispatchers.Default
                override fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Main
            }

    abstract suspend fun run(params: Params): Either<FailureType, T>

    override operator fun invoke(onResult: (Either<FailureType, T>) -> Unit, params: Params) {
        job = GlobalScope.async(dispatcherProvider.provideExecutor()) { run(params) }
        GlobalScope.launch(dispatcherProvider.provideDispatcher()) { onResult.invoke(job!!.await()) }
    }

    override fun cancel() {
        job?.cancel()
    }

    object None

}

abstract class UseCaseCustomDispatcher<out T : Any, in Params>(private val customDispatcherProvider: CoroutineDispatcherProvider) : UseCase<T, Params>() {
    override val dispatcherProvider: CoroutineDispatcherProvider
        get() = customDispatcherProvider
}