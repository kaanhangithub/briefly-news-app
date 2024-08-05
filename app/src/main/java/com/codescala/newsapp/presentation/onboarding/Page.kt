package com.codescala.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.codescala.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Stay Informed",
        description = "Welcome to Briefly! Stay updated with the latest news from around the world, all in one place. Explore top stories and breaking news tailored to your interests.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Save for Later",
        description = "Found an article you want to revisit? Easily bookmark your favorite stories with a tap. Access them anytime, even when you're offline.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Dive Deeper",
        description = "Read full articles, share interesting stories with friends, and visit the original source. Manage your bookmarks seamlessly with just a few taps.",
        image = R.drawable.onboarding3
    )
)