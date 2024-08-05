package com.codescala.newsapp.domain.usecases.news

import com.codescala.newsapp.domain.model.Article
import com.codescala.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedArticlesUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getArticles()
    }
}