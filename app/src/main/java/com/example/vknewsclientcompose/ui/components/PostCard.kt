package com.example.vknewsclientcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.vknewsclientcompose.ui.theme.VKNewsClientComposeTheme

@Composable
fun PostCard() {
    Card(
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
            PostHeader()
            Content()
            Statistics()
        }
    }
}

@Composable
private fun PostHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.post_comunity_thumbnail),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "name",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "14:00",
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
private fun Content() {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Text(text = LoremIpsum(10).values.toList().toString())
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.post_content_image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
private fun Statistics() {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(icon = Icons.Outlined.Person, text = "1 200")
        }
        IconWithText(icon = Icons.Outlined.Share, text = "123")
        Spacer(modifier = Modifier.width(16.dp))
        IconWithText(icon = Icons.Outlined.MailOutline, text = "48")
        Spacer(modifier = Modifier.width(16.dp))
        IconWithText(icon = Icons.Outlined.FavoriteBorder, text = "45 555")
    }
}

@Composable
private fun IconWithText(
    icon: ImageVector,
    text: String,
) {
    Row(
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

@Preview
@Composable
private fun PreviewPostCardLight() {
    VKNewsClientComposeTheme(darkTheme = false) {
        PostCard()
    }
}

@Preview
@Composable
private fun PreviewPostCardDark() {
    VKNewsClientComposeTheme(darkTheme = true) {
        PostCard()
    }
}