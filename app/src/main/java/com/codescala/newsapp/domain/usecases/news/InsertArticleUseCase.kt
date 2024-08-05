package com.codescala.newsapp.domain.usecases.news

import com.codescala.newsapp.domain.model.Article
import com.codescala.newsapp.domain.repository.NewsRepository

class InsertArticleUseCase (
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        repository.insertArticle(article = article)
    }
}