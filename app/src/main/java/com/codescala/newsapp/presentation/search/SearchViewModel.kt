package com.codescala.newsapp.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codescala.newsapp.domain.usecases.news.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchNewsUseCase
) : ViewModel() {

    var state = mutableStateOf(SearchState())
        private set




    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                state.value = state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = useCase(
            searchQuery = state.value.searchQuery,
            sources = listOf(
                "bbc-news",
                "cnn",
                "fox-news",
                "the-wall-street-journal",
                "the-washington-post")
        ).cachedIn(viewModelScope)
        state.value = state.value.copy(articles = articles)
    }
}