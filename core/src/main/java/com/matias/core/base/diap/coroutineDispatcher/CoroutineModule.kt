package com.matias.core.base.diap.coroutineDispatcher

import com.matias.domain.base.usecase.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
class CoroutineModule {
    
    companion object {
        const val DISPATCHER_BG_UI = "DISPATCHER_BG_UI"
        const val DISPATCHER_BG_BG = "DISPATCHER_BG_BG"
    }
    
    @Provides
    @Singleton
    @Named(DISPATCHER_BG_UI)
    fun provideCoroutineDispatcherProviderBGtoUI(): CoroutineDispatcherProvider =
            object : CoroutineDispatcherProvider {
                override fun provideExecutor(): CoroutineDispatcher = Dispatchers.Default
                override fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Main
            }
    
    @Provides
    @Singleton
    @Named(DISPATCHER_BG_BG)
    fun provideCoroutineDispatcherProviderBGtoBG(): CoroutineDispatcherProvider =
            object : CoroutineDispatcherProvider {
                override fun provideExecutor(): CoroutineDispatcher = Dispatchers.Default
                override fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Default
            }
    
}