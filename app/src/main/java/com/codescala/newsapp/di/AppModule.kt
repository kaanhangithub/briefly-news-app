package com.codescala.newsapp.di

import android.app.Application
import androidx.room.Room
import com.codescala.newsapp.data.local.NewsDao
import com.codescala.newsapp.data.local.NewsDatabase
import com.codescala.newsapp.data.local.NewsTypeConverter
import com.codescala.newsapp.data.manager.LocalUserManagerImpl
import com.codescala.newsapp.data.remote.NewsApi
import com.codescala.newsapp.data.repository.NewsRepositoryImpl
import com.codescala.newsapp.domain.manager.LocalUserManager
import com.codescala.newsapp.domain.repository.NewsRepository
import com.codescala.newsapp.domain.usecases.news.DeleteArticleUseCase
import com.codescala.newsapp.domain.usecases.news.GetNewsUseCase
import com.codescala.newsapp.domain.usecases.news.GetSavedArticlesUseCase
import com.codescala.newsapp.domain.usecases.news.InsertArticleUseCase
import com.codescala.newsapp.domain.usecases.news.SearchNewsUseCase
import com.codescala.newsapp.domain.usecases.onboarding.ReadAppEntry
import com.codescala.newsapp.domain.usecases.onboarding.SaveAppEntry
import com.codescala.newsapp.utils.Constants.BASE_URL
import com.codescala.newsapp.utils.Constants.DATA_BASE_NAME
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
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = DATA_BASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase = GetNewsUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(newsRepository: NewsRepository): SearchNewsUseCase = SearchNewsUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideGetArticlesUseCase(newsRepository: NewsRepository): GetSavedArticlesUseCase = GetSavedArticlesUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideInsertArticleUseCase(newsRepository: NewsRepository): InsertArticleUseCase = InsertArticleUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideDeleteArticleUseCase(newsRepository: NewsRepository): DeleteArticleUseCase = DeleteArticleUseCase(newsRepository)
}