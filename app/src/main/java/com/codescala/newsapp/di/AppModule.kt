package com.codescala.newsapp.di

import android.app.Application
import com.codescala.newsapp.data.manager.LocalUserManagerImpl
import com.codescala.newsapp.data.remote.NewsApi
import com.codescala.newsapp.data.repository.NewsRepositoryImpl
import com.codescala.newsapp.domain.manager.LocalUserManager
import com.codescala.newsapp.domain.repository.NewsRepository
import com.codescala.newsapp.domain.usecases.news.GetNewsUseCase
import com.codescala.newsapp.domain.usecases.news.SearchNewsUseCase
import com.codescala.newsapp.domain.usecases.onboarding.ReadAppEntry
import com.codescala.newsapp.domain.usecases.onboarding.SaveAppEntry
import com.codescala.newsapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideReadAppEntry(localUserManager: LocalUserManager): ReadAppEntry = ReadAppEntry(localUserManager)

    @Provides
    @Singleton
    fun provideSaveAppEntry(localUserManager: LocalUserManager): SaveAppEntry = SaveAppEntry(localUserManager)

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase = GetNewsUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(newsRepository: NewsRepository): SearchNewsUseCase = SearchNewsUseCase(newsRepository)
}