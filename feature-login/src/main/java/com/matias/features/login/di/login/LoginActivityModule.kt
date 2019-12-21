package com.matias.features.login.di.login

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.matias.features.login.ui.login.LoginPagerAdapter
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule(private var activity: AppCompatActivity) {

	@Provides
	fun provideActivity(): AppCompatActivity = activity

	@Provides
	fun provideFragmentManager(activity: AppCompatActivity): FragmentManager =
		activity.supportFragmentManager

	@Provides
	fun provideLoginPagerAdapter(fm: FragmentManager): LoginPagerAdapter = LoginPagerAdapter(fm, 0)

}