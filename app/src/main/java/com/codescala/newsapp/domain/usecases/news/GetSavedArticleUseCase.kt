package com.codescala.newsapp.domain.usecases.news

import com.codescala.newsapp.domain.model.Article
import com.codescala.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return repository.getArticle(url = url)
    }
}