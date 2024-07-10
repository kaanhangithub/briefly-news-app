package com.codescala.newsapp.domain.usecases.news

import com.codescala.newsapp.domain.repository.NewsRepository

class GetNewsUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>) = newsRepository.getNews(sources)
}