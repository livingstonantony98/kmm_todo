@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kmmtodos.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmmtodos.LocalAppContainer
import com.example.shared.data.model.Todo
import com.example.shared.ui.MyViewmodel
import com.example.shared.ui.UiState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoAppScreen(
    // Your ViewModel injection method remains the same.
    viewModel: MyViewmodel = viewModel(
        factory = LocalAppContainer.current.todoViewModelFactory,
    )
) {
    // Corrected to use the new `uiState` property from the ViewModel.
    val uiState by viewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo List (Compose)") },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = uiState) {
                is UiState.Loading -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Text(
                            "Loading todos...",
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }

                is UiState.Success -> {
                    val todos = currentState.data
                    if (todos.isEmpty()) {
                        Text(
                            "No todos found. Add Some!",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        // Pass the list to your content Composable.
                        TodoListContent(todos = todos, modifier = Modifier.fillMaxSize())
                    }
                }

                is UiState.Error -> {
                    ErrorContent(message = currentState.message)
                }
            }
        }
    }
}
@Composable
fun TodoListContent(todos: List<Todo>, modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = todos,
            key = { todo -> todo.id }

        ) { todo ->

            TodoItem(todo = todo)
//            HorizontalDivider()
        }
    }
}
@Composable
fun TodoItem(todo: Todo, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)


    ) {

        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = {}
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else null,
                    color = if (todo.isCompleted) Color.Gray else MaterialTheme.colorScheme.onSurface

                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "User ID: ${todo.userId}",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
fun ErrorContent(message: String, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Log.d("TAG", "ErrorContent: $message")

        Text(
            text = "😔", // Sad emoji
            fontSize = 48.sp,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "An error occurred:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

