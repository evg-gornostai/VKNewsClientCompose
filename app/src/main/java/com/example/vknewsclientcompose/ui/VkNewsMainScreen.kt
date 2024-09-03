package com.example.vknewsclientcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.vknewsclientcompose.ui.components.PostCard
import com.example.vknewsclientcompose.ui.entity.NavigationItem

@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val navigationItems = listOf(
        NavigationItem.Home,
        NavigationItem.Favourite,
        NavigationItem.Profile,
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                navigationItems.fastForEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
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
        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(8.dp)
        ) {
            PostCard()
        }
    }
}