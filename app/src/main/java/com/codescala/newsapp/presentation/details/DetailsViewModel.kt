package com.codescala.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codescala.newsapp.domain.model.Article
import com.codescala.newsapp.domain.usecases.news.DeleteArticleUseCase
import com.codescala.newsapp.domain.usecases.news.GetSavedArticleUseCase
import com.codescala.newsapp.domain.usecases.news.InsertArticleUseCase
import com.codescala.newsapp.utils.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val insertArticleUseCase: InsertArticleUseCase,
    private val getSavedArticleUseCase: GetSavedArticleUseCase,
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.SaveDeleteArticle -> {
                viewModelScope.launch {
                    val article = getSavedArticleUseCase(url = event.article.url)
                    if (article == null) {
                        saveArticle(article = event.article)
                    } else {
                        deleteArticle(article = event.article)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        deleteArticleUseCase(article = article)
        sideEffect = UIComponent.Toast("Article deleted")
    }

    private suspend fun saveArticle(article: Article) {
        insertArticleUseCase(article = article)
        sideEffect = UIComponent.Toast("Article Inserted")
    }
}