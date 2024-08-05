package com.codescala.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codescala.newsapp.domain.usecases.news.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    val news = getNewsUseCase(
        sources = listOf(
            "bbc-news",
            "cnn",
            "fox-news",
            "google-news",
            "the-verge",
            "techcrunch"
        )
    ).cachedIn(viewModelScope)
}