package com.vimal.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vimal.myapplication.screen.PostScreen
import com.vimal.myapplication.screen.UserScreen
import com.vimal.myapplication.ui.theme.MyApplicationTheme
import com.vimal.myapplication.viewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PostViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val posts by viewModel.posts.collectAsState()
                val isPostLoading by viewModel.isPostLoading.collectAsState()
                val users by viewModel.users.collectAsState()
                val isUserLoading by viewModel.isUserLoading.collectAsState()
                var isPostClick by remember { mutableStateOf(false) }
                var isUserClick by remember { mutableStateOf(false) }

                // Handle back press
                BackHandler {
                    if (isPostClick) {
                        // If we're on the PostScreen, set isPostClick to false to show the buttons again
                        isPostClick = false
                    } else if (isUserClick) {
                        // If we're on the UserScreen, set isUserClick to false to show the buttons again
                        isUserClick = false
                    } else {
                        finish()
                    }
                }

                val title = if (isPostClick) {
                    getString(R.string.posts)
                } else if (isUserClick) {
                    getString(R.string.users)
                } else {
                    getString(R.string.app_name)
                }

                Scaffold(
                    topBar = { TopAppBar(title = { Text(title) }) }
                ) { padding ->

                   if (isUserClick) {
                       UserScreen(
                           modifier = Modifier.padding(padding).fillMaxSize(),
                           isLoading = isUserLoading,
                           users = users
                       )
                   } else if (isPostClick) {
                       PostScreen(
                           modifier = Modifier.padding(padding).fillMaxSize(),
                           isLoading = isPostLoading,
                           posts = posts
                       )
                   } else {
                       Column(
                           modifier = Modifier.padding(padding).fillMaxSize(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.spacedBy(30.dp)
                       ) {
                           Button(onClick = {
                               isPostClick = true
                           }) {
                               Text(getString(R.string.posts))
                           }

                           Button(onClick = { isUserClick = true }) {
                               Text(getString(R.string.users))
                           }
                       }

                   }
                }
            }
        }
    }
}