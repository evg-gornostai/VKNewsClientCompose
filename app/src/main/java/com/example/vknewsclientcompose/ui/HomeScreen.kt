package com.example.vknewsclientcompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vknewsclientcompose.MainViewModel
import com.example.vknewsclientcompose.ui.components.PostCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
) {
    val feed = viewModel.feed.observeAsState(emptyList())
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = feed.value, key = { it.id }) { post ->
            val dismissBoxState = rememberSwipeToDismissBoxState()
            if (dismissBoxState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                viewModel.onDeleteItemRequested(post)
            }
            SwipeToDismissBox(
                modifier = Modifier.animateItemPlacement(),
                state = dismissBoxState,
                enableDismissFromStartToEnd = false,
                backgroundContent = {},
            ) {
                PostCard(
                    feedPost = post,
                    onViewsClickListener = {
                        viewModel.onUpdateCount(feedPost = post, item = it)
                    },
                    onSharesClickListener = {
                        viewModel.onUpdateCount(feedPost = post, item = it)
                    },
                    onCommentsClickListener = {
                        viewModel.onUpdateCount(feedPost = post, item = it)
                    },
                    onLikesClickListener = {
                        viewModel.onUpdateCount(feedPost = post, item = it)
                    },
                )
            }
        }
    }
}