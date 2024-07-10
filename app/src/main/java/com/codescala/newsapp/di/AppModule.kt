package com.codescala.newsapp.di

import android.app.Application
import com.codescala.newsapp.data.manager.LocalUserManagerImpl
import com.codescala.newsapp.domain.manager.LocalUserManager
import com.codescala.newsapp.domain.usecases.ReadAppEntry
import com.codescala.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideReadAppEntry(localUserManager: LocalUserManager): ReadAppEntry  = ReadAppEntry(localUserManager)

    @Provides
    @Singleton
    fun provideSaveAppEntry(localUserManager: LocalUserManager): SaveAppEntry = SaveAppEntry(localUserManager)
}