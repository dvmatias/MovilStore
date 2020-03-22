package com.matias.features.diimport com.matias.data.cache.featureflag.FeatureFlaghCacheimport com.matias.data.cache.mainposition.MainPositionCacheimport com.matias.data.platform.NetworkHandlerimport com.matias.data.provider.globalconfig.FeatureFlagProviderImplimport com.matias.data.provider.mainposition.MainPositionProviderImplimport com.matias.data.service.main.MainPositionApiimport com.matias.data.service.main.MainPositionServiceimport com.matias.domain.provider.globalconfig.FeatureFlagProviderimport com.matias.domain.provider.mainposition.MainPositionProviderimport com.matias.domain.usecases.globalconfig.GetFeatureEnableUseCaseimport com.matias.domain.usecases.mainposition.GetMainPositionUseCaseimport dagger.Moduleimport dagger.Providesimport retrofit2.Retrofit@Moduleclass MainModule {	@Provides	@MainScope	fun provideFeatureFlagProvider(featureFlaghCache: FeatureFlaghCache): FeatureFlagProvider =		FeatureFlagProviderImpl(featureFlaghCache)	@Provides	@MainScope	fun provideGetFeatureEnableUseCase(featureFlagProvider: FeatureFlagProvider): GetFeatureEnableUseCase =		GetFeatureEnableUseCase(featureFlagProvider)	@Provides	@MainScope	fun provideMainPositionApi(retrofit: Retrofit): MainPositionApi =		MainPositionService(retrofit)	@Provides	@MainScope	fun provideMainPositionProvider(		mainPositionApi: MainPositionApi,		mainPositionCache: MainPositionCache,		networkHandler: NetworkHandler	): MainPositionProvider =		MainPositionProviderImpl(mainPositionApi, mainPositionCache, networkHandler)	@Provides	@MainScope	fun provideGetMainPositionUseCase(mainPositionProvider: MainPositionProvider): GetMainPositionUseCase =		GetMainPositionUseCase(mainPositionProvider)}