//
// Created by Philosopher on 08/08/25.
//


import Foundation
import SwiftUI
import ComposeApp


struct Todo: Identifiable {
    let id: Int
    let userId: Int
    let title: String
    var isCompleted: Bool
}

// Define this in a relevant place, often with your ViewModel or model types
enum UiState {
    case loading
    case success(data: [Todo])
    case error(message: String)
}


//struct TodoAppScreenView: View {
//    
//    
//    // @StateObject ensures the ViewModel's lifecycle is tied to this view's lifecycle.
//    // Use @ObservedObject if the ViewModel is created and managed by a parent and passed in.
//    @StateObject private var viewModel: MyViewModel = MyViewModel()
//
// 
//    var body: some View {
//       
//        // For iOS 16+ and more complex navigation, NavigationStack is often preferred.
//        NavigationView {
//            ZStack { // ZStack is used here to easily center content like loading/error states.
//                // Content is layered; views appearing later are on top.
//
//                // Switch on the current state from the ViewModel to display appropriate UI
//                switch viewModel.uiState {
//                case .loading:
//                    VStack(spacing: 16) {
//                        ProgressView() // SwiftUI's equivalent of CircularProgressIndicator
//                            .scaleEffect(1.5) // Optional: makes the indicator larger
//                        Text("Loading todos...")
//                            .font(.headline)
//                            .foregroundColor(.secondary)
//                    }
//                case .success(let todos):
//                    if todos.isEmpty {
//                        Text("No todos found. Add Some!")
//                            .font(.title2)
//                            .multilineTextAlignment(.center)
//                            .foregroundColor(.secondary)
//                            .padding(.horizontal)
//                    } else {
//                        // Assuming TodoListContentView is defined as converted previously
//                        TodoListContentView(todos: todos)
//                    }
//                case .error(let message):
//                    // Assuming ErrorContentView is defined as converted previously
//                    ErrorContentView(message: message)
//                        .padding() // Give the error content some breathing room
//                }
//            }
//            .navigationTitle("Todo List (SwiftUI)") // Sets the title in the navigation bar
//            // .navigationBarTitleDisplayMode(.inline) // Or .large, .automatic
//            .onAppear {
//                // When the view appears, trigger the ViewModel to fetch todos.
//                // This is a common place for initial data loading.
//                viewModel.fetchTodos()
//            }
//
//        }
//    }
//}

// MARK: - Preview Provider
//struct TodoAppScreenView_Previews: PreviewProvider {
//    static var previews: some View {
//        // --- Previewing different states ---
//
//        // 1. ViewModel for Loading State
//        let loadingViewModel = MyViewModel()
//        // (uiState is .loading by default in MyViewModel)
//
//        // 2. ViewModel for Success State (with data)
//        let successViewModel = MyViewModel()
//        successViewModel.uiState = .success(data: [
//            Todo(id: 1, userId: 101, title: "Learn SwiftUI", isCompleted: false),
//            Todo(id: 2, userId: 102, title: "Build an App", isCompleted: true),
//            Todo(id: 3, userId: 101, title: "Ship it!", isCompleted: false)
//        ])
//
//        // 3. ViewModel for Success State (empty)
//        let emptySuccessViewModel = MyViewModel()
//        emptySuccessViewModel.uiState = .success(data: [])
//
//        // 4. ViewModel for Error State
//        let errorViewModel = MyViewModel()
//        errorViewModel.uiState = .error(message: "Failed to load todos from server. Please check your connection.")
//
//        // Group allows multiple previews
//        Group {
//            TodoAppScreenView(viewModel: loadingViewModel)
//                .previewDisplayName("Loading State")
//
//            TodoAppScreenView(viewModel: successViewModel)
//                .previewDisplayName("Success with Todos")
//
//            TodoAppScreenView(viewModel: emptySuccessViewModel)
//                .previewDisplayName("Success (Empty List)")
//
//            TodoAppScreenView(viewModel: errorViewModel)
//                .previewDisplayName("Error State")
//        }
//    }
//}
//

struct TodoListContentView: View {
    let todos: [Todo]
    var body: some View {
        List {
            ForEach(todos) { todo in
                TodoItemView(todo: todo)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity) // .fillMaxSize()
        .padding(.horizontal, 16) // .padding(horizontal = 16.dp)
    }
}

// MARK: - Preview
struct TodoListContentView_Previews: PreviewProvider {
    static let sampleTodos = [
        Todo(id: 1, userId: 1, title: "First task", isCompleted: false),
        Todo(id: 2, userId: 1, title: "Second task, completed", isCompleted: true),
        Todo(id: 3, userId: 2, title: "Another user's task", isCompleted: false)
    ]

    static var previews: some View {
        TodoListContentView(todos: sampleTodos)
    }
}

struct TodoItemView: View {
    let todo: Todo

    var body: some View {
        HStack(alignment: .center, spacing: 16) {
            Image(systemName: todo.isCompleted ? "checkmark.square.fill" : "square")
                .font(.title2)
                .foregroundColor(todo.isCompleted ? .accentColor : .secondary)
                .onTapGesture {
                    print("Checkbox tapped for todo: \(todo.title)")
                }

            VStack(alignment: .leading, spacing: 4) { // Corresponds to Column
                Text(todo.title)
                    .font(.headline) // Approximate for titleMedium
                    .strikethrough(todo.isCompleted, color: .gray) // For TextDecoration.LineThrough
                    .foregroundColor(todo.isCompleted ? .gray : .primary) // .primary is like onSurface

                Text("User ID: \(todo.userId)")
                    .font(.caption) // Approximate for bodySmall
                    .italic()       // For FontStyle.Italic
            }
            .layoutPriority(1) // Helps this VStack take available space, similar to weight(1f)

        }
        .padding(16) // Padding for the entire item
        .frame(maxWidth: .infinity, alignment: .leading) // Ensures the HStack tries to fill width, content aligned left
        .background(
            RoundedRectangle(cornerRadius: 8)
                .fill(Color(UIColor.systemBackground)) // Adapts to light/dark mode
                .shadow(color: .gray.opacity(0.4), radius: 3, x: 0, y: 2) // Basic shadow for card effect
        )
    }
}

struct TodoItemView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            TodoItemView(todo: Todo(id: 1, userId: 101, title: "Buy groceries", isCompleted: false))
                .padding() // Add padding around the preview item itself for better visibility

            TodoItemView(todo: Todo(id: 2, userId: 102, title: "Write SwiftUI code", isCompleted: true))
                .padding()

            TodoItemView(todo: Todo(id: 3, userId: 103, title: "A very long task title that should wrap or truncate effectively within the given space provided by the layout constraints.", isCompleted: false))
                .padding()
        }
        .previewLayout(.sizeThatFits) // or .fixed(width: 300, height: 100)
    }
}

struct ErrorContentView: View {
    let message: String

    var body: some View {
        VStack(spacing: 16) {
            Text("😔")
                .font(.system(size: 48))
                .foregroundColor(.red)

//            Spacer.frame(height: 1=6)

            Text("An error occurred:")
                .font(.headline)

            Text(message)
                .font(.body)
                .foregroundColor(.red)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity) // Makes the VStack fill all available space.
        .padding(16)
    }
}

struct ErrorContentView_Previews: PreviewProvider {
    static var previews: some View {
        ErrorContentView(message: "Something went wrong. Please try again.")
    }
}
