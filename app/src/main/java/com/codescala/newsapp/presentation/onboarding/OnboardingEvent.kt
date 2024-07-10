package com.codescala.newsapp.presentation.onboarding

sealed class OnboardingEvent {
    object SaveAppEntry : OnboardingEvent()
}