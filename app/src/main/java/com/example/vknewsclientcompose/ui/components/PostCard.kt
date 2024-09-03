package com.example.vknewsclientcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.vknewsclientcompose.R
import com.example.vknewsclientcompose.domain.entity.FeedPost
import com.example.vknewsclientcompose.domain.entity.StatisticItem
import com.example.vknewsclientcompose.domain.entity.StatisticType
import com.example.vknewsclientcompose.ui.theme.VKNewsClientComposeTheme

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onViewsClickListener: (StatisticItem) -> Unit,
    onSharesClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    onLikesClickListener: (StatisticItem) -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
        ) {
            PostHeader(feedPost = feedPost)
            Content(feedPost = feedPost)
            Statistics(
                statistics = feedPost.statistics,
                onViewsClickListener = onViewsClickListener,
                onSharesClickListener = onSharesClickListener,
                onCommentsClickListener = onCommentsClickListener,
                onLikesClickListener = onLikesClickListener,
            )
        }
    }
}

@Composable
private fun PostHeader(
    feedPost: FeedPost,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = feedPost.communityName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
private fun Content(
    feedPost: FeedPost,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Text(text = feedPost.contentText)
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = feedPost.contentImageResId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onViewsClickListener: (StatisticItem) -> Unit,
    onSharesClickListener: (StatisticItem) -> Unit,
    onCommentsClickListener: (StatisticItem) -> Unit,
    onLikesClickListener: (StatisticItem) -> Unit,
) {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            val viewsItem = statistics.findItemByType(StatisticType.VIEWS)
            IconWithText(
                icon = Icons.Outlined.Person,
                text = viewsItem.value.toString(),
                onItemClickListener = { onViewsClickListener(viewsItem) }
            )
        }
        val sharesItem = statistics.findItemByType(StatisticType.SHARES)
        IconWithText(
            icon = Icons.Outlined.Share,
            text = sharesItem.value.toString(),
            onItemClickListener = { onSharesClickListener(sharesItem) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        val commentsItem = statistics.findItemByType(StatisticType.COMMENTS)
        IconWithText(
            icon = Icons.Outlined.MailOutline,
            text = commentsItem.value.toString(),
            onItemClickListener = { onCommentsClickListener(commentsItem) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        val likesItem = statistics.findItemByType(StatisticType.LIKES)
        IconWithText(
            icon = Icons.Outlined.FavoriteBorder,
            text = likesItem.value.toString(),
            onItemClickListener = { onLikesClickListener(likesItem) }
        )
    }
}

private fun List<StatisticItem>.findItemByType(type: StatisticType): StatisticItem {
    return find { it.type == type } ?: throw IllegalStateException()
}

@Composable
private fun IconWithText(
    icon: ImageVector,
    text: String,
    onItemClickListener: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable {
                onItemClickListener()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = rememberVectorPainter(image = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}