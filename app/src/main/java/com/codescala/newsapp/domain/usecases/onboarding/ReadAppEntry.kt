package com.codescala.newsapp.domain.usecases.onboarding

import com.codescala.newsapp.domain.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke() = localUserManager.readAppEntry()
}