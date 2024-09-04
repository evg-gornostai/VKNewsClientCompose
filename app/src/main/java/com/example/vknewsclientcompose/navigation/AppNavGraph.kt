package com.example.vknewsclientcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favouriteContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewsFeed.route,
    ) {
        composable(Screen.NewsFeed.route) {
            homeScreenContent()
        }
        composable(Screen.Favourite.route) {
            favouriteContent()
        }
        composable(Screen.Profile.route) {
            profileContent()
        }
    }
}