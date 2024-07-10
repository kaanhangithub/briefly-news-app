package com.codescala.newsapp.data.remote.dto

import com.codescala.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)