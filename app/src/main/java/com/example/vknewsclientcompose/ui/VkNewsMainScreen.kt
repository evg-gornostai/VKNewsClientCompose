package com.example.vknewsclientcompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.vknewsclientcompose.MainViewModel
import com.example.vknewsclientcompose.domain.entity.FeedPost
import com.example.vknewsclientcompose.ui.components.PostCard
import com.example.vknewsclientcompose.ui.entity.NavigationItem

@Composable
fun MainScreen(viewModel: MainViewModel) {
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
        val fedPost = viewModel.feedPost.observeAsState(FeedPost())

        PostCard(
            modifier = Modifier
                .padding(8.dp),
            feedPost = fedPost.value,
            onStatisticItemClickListener = viewModel::updateCount
        )
    }
}