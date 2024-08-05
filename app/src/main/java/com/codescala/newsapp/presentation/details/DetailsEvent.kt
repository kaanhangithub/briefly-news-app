package com.codescala.newsapp.presentation.details

import com.codescala.newsapp.domain.model.Article

sealed class DetailsEvent {
    data class SaveDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}