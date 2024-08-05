package com.codescala.newsapp.presentation.bookmark

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codescala.newsapp.domain.usecases.news.GetSavedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel
@Inject
constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase
) : ViewModel() {

    var state = mutableStateOf(BookmarkState())
        private set

    init {
        getArticles()
    }

    private fun getArticles() {
        getSavedArticlesUseCase().onEach {
            state.value = state.value.copy(articles = it.asReversed())
        }.launchIn(viewModelScope)
    }
}