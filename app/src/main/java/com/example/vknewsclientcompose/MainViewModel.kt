package com.example.vknewsclientcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vknewsclientcompose.domain.entity.FeedPost
import com.example.vknewsclientcompose.domain.entity.StatisticItem

class MainViewModel : ViewModel() {
    private val _feedPost = MutableLiveData<FeedPost>(FeedPost())
    val feedPost: LiveData<FeedPost> = _feedPost

    fun updateCount(newItem: StatisticItem) {
        val oldStatistics = _feedPost.value?.statistics ?: throw IllegalStateException()
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == newItem.type) {
                    oldItem.copy(value = oldItem.value + 1)
                } else {
                    oldItem
                }
            }
        }
        _feedPost.value = _feedPost.value?.copy(statistics = newStatistics)
    }
}