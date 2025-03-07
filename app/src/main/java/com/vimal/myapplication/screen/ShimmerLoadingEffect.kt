package com.vimal.myapplication.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun ShimmerLoadingEffect(modifier: Modifier, isPost : Boolean = true) {
    LazyColumn(modifier = modifier) {
        items(10) { // Show 5 items while loading
            PostItemShimmer(isPost)
        }
    }
}

@Composable
fun PostItemShimmer(isPost : Boolean) {
    val title = if (isPost) "sunt aut facere repellat provident occaecati excepturi optio reprehenderit" else "Shimmer Title"
    val body = if (isPost) "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto" else "Shimmer Body"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
        ,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.placeholder(visible = true, highlight = PlaceholderHighlight.shimmer())
            )
        }
    }
}