package com.example.vknewsclientcompose.domain.entity

data class StatisticItem(
    val type: StatisticType,
    val value: Int = 0,
)

enum class StatisticType {
    VIEWS, SHARES, COMMENTS, LIKES,
}
