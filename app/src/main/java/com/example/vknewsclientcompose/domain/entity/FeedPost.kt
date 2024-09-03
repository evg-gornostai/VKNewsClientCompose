package com.example.vknewsclientcompose.domain.entity

import com.example.vknewsclientcompose.R

data class FeedPost(
    val id: Int,
    val communityName: String = "communityName",
    val publicationDate: String= "publicationDate",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "contentText contentText contentText contentText contentText contentText ",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, value = 123),
        StatisticItem(type = StatisticType.SHARES, value = 5445),
        StatisticItem(type = StatisticType.COMMENTS, value = 994),
        StatisticItem(type = StatisticType.LIKES, value = 4343),
    )
)
