package com.codescala.newsapp.presentation.details

sealed class DetailsEvent {
    object SaveArticle : DetailsEvent()
}