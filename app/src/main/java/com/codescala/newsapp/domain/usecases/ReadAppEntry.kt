package com.codescala.newsapp.domain.usecases

import com.codescala.newsapp.domain.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke() = localUserManager.readAppEntry()
}