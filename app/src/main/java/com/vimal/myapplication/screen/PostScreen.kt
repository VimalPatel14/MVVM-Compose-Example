package com.vimal.myapplication.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vimal.myapplication.model.Post
import com.vimal.myapplication.ui.theme.MyApplicationTheme

@Composable
fun PostScreen(modifier: Modifier, isLoading : Boolean, posts: List<Post>) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = modifier,
            color = Color.Red
        )
    } else {
        LazyColumn(modifier = modifier) {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostItem() {
    MyApplicationTheme {
        PostItem(
            post = Post(
                title = "Sample Post Title",
                body = "This is a sample post body to showcase the preview."
            )
        )
    }
}

