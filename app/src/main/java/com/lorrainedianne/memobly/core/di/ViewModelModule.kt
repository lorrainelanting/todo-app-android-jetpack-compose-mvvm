package com.lorrainedianne.memobly.core.di

import com.lorrainedianne.memobly.presentation.route.NavManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Singleton
    @Provides
    fun providesNavManager(): NavManager {
        return NavManager()
    }
}