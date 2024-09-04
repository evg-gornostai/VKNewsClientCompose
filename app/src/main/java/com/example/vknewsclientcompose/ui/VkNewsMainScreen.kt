package com.example.vknewsclientcompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vknewsclientcompose.MainViewModel
import com.example.vknewsclientcompose.navigation.AppNavGraph
import com.example.vknewsclientcompose.navigation.Screen
import com.example.vknewsclientcompose.navigation.rememberNavigationState
import com.example.vknewsclientcompose.ui.components.PostCard
import com.example.vknewsclientcompose.ui.entity.NavigationItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRout = navBackStackEntry?.destination?.route
                val navigationItems = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile,
                )
                navigationItems.forEach { navigationItem ->
                    NavigationBarItem(
                        selected = currentRout == navigationItem.screen.route,
                        onClick = {
                            navigationState.navigateTo(navigationItem.screen.route)
                        },
                        icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = navigationItem.titleResId))
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navigationState.navHostController,
            homeScreenContent = { HomeScreen(viewModel = viewModel, paddingValues = innerPadding) },
            favouriteContent = { TextCounter("Favourite") },
            profileContent = { TextCounter("Profile") },
        )
    }
}

@Composable
fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    Text(
        modifier = Modifier
            .clickable { count++ }
            .fillMaxSize(),
        text = "$name $count",
    )
}