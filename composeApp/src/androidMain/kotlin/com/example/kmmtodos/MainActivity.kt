package com.example.kmmtodos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmmtodos.data.model.Todo
import com.example.kmmtodos.ui.widgets.ErrorContent
import com.example.kmmtodos.ui.widgets.TodoAppScreen
import com.example.kmmtodos.ui.widgets.TodoItem
import com.example.kmmtodos.ui.widgets.TodoListContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            TodoAppScreen()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}



@Preview(showBackground = true, name = "Todo Item Preview (Pending)")
@Composable
fun TodoItemPendingPreview() {
    MaterialTheme {
        TodoItem(todo = Todo(id = 1, userId = 1, title = "Buy groceries", isCompleted = false))
    }
}

@Preview(showBackground = true, name = "Todo Item Preview (Completed)")
@Composable
fun TodoItemCompletedPreview() {
    MaterialTheme {
        TodoItem(todo = Todo(id = 2, userId = 1, title = "Read a book", isCompleted = true))
    }
}

@Preview(showBackground = true, name = "Todo List Preview")
@Composable
fun TodoListContentPreview() {
    MaterialTheme {
        TodoListContent(
            todos = listOf(
                Todo(1, 1, "First item - very long title to see how it wraps around the screen", false),
                Todo(2, 1, "Second item", true),
                Todo(3, 2, "Third item", false)
            )
        )
    }
}

@Preview(showBackground = true, name = "Loading State Preview", widthDp = 360, heightDp = 640)
@Composable
fun TodoAppScreenLoadingPreview() {
    MaterialTheme {
        // Simulate a Loading state for preview (can't directly set ViewModel state here easily for preview)
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
            Text("Loading todos...", modifier = Modifier.padding(top = 60.dp))
        }
    }
}

@Preview(showBackground = true, name = "Error State Preview", widthDp = 360, heightDp = 640)
@Composable
fun TodoAppScreenErrorPreview() {
    MaterialTheme {
        ErrorContent(message = "Failed to connect to the server. Please check your internet connection.")
    }
}

@Preview(showBackground = true, name = "Empty State Preview", widthDp = 360, heightDp = 640)
@Composable
fun TodoAppScreenEmptyPreview() {
    MaterialTheme {
        // Simulate an empty success state
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No todos found. Add some!")
        }
    }
}