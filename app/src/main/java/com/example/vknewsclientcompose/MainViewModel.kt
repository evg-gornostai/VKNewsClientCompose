package com.example.vknewsclientcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclientcompose.domain.entity.FeedPost
import com.example.vknewsclientcompose.domain.entity.StatisticItem
import com.example.vknewsclientcompose.ui.entity.NavigationItem

class MainViewModel : ViewModel() {
    private val posts = buildList {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val _feed = MutableLiveData<List<FeedPost>>(posts)
    val feed: LiveData<List<FeedPost>> = _feed

    fun onUpdateCount(feedPost: FeedPost, item: StatisticItem) {
        val oldPosts = _feed.value?.toMutableList() ?: return
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(value = oldItem.value + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        _feed.value = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
    }

    fun onDeleteItemRequested(item: FeedPost) {
        val modifiedList = _feed.value?.toMutableList() ?: return
        modifiedList.remove(item)
        _feed.value = modifiedList
    }
}