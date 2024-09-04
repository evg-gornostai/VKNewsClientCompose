package com.example.vknewsclientcompose.navigation

private const val ROUTE_NEWS_FEED = "news_feed"
private const val ROUTE_FAVOURITE = "favourite"
private const val ROUTE_PROFILE = "profile"

sealed class Screen(
    val route: String
) {
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favourite : Screen(ROUTE_FAVOURITE)
    object Profile : Screen(ROUTE_PROFILE)
}