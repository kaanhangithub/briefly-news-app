package com.codescala

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codescala.newsapp.domain.usecases.onboarding.ReadAppEntry
import com.codescala.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readAppEntryUseCase: ReadAppEntry
) : ViewModel() {
    var splashConditon by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        readAppEntryUseCase().onEach { shouldSkipOnboarding ->
            startDestination = if (shouldSkipOnboarding) {
                Route.NewsNavigation.route
            } else {
                Route.AppStartNavigation.route
            }
            delay(300)
            splashConditon = false
        }.launchIn(viewModelScope)
    }
}