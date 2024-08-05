package com.codescala.newsapp.presentation.bookmark

import com.codescala.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)